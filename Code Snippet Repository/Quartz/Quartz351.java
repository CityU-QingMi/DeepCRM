    protected List<SchedulerStateRecord> findFailedInstances(Connection conn)
        throws JobPersistenceException {
        try {
            List<SchedulerStateRecord> failedInstances = new LinkedList<SchedulerStateRecord>();
            boolean foundThisScheduler = false;
            long timeNow = System.currentTimeMillis();
            
            List<SchedulerStateRecord> states = getDelegate().selectSchedulerStateRecords(conn, null);

            for(SchedulerStateRecord rec: states) {
        
                // find own record...
                if (rec.getSchedulerInstanceId().equals(getInstanceId())) {
                    foundThisScheduler = true;
                    if (firstCheckIn) {
                        failedInstances.add(rec);
                    }
                } else {
                    // find failed instances...
                    if (calcFailedIfAfter(rec) < timeNow) {
                        failedInstances.add(rec);
                    }
                }
            }
            
            // The first time through, also check for orphaned fired triggers.
            if (firstCheckIn) {
                failedInstances.addAll(findOrphanedFailedInstances(conn, states));
            }
            
            // If not the first time but we didn't find our own instance, then
            // Someone must have done recovery for us.
            if ((!foundThisScheduler) && (!firstCheckIn)) {
                // FUTURE_TODO: revisit when handle self-failed-out impl'ed (see FUTURE_TODO in clusterCheckIn() below)
                getLog().warn(
                    "This scheduler instance (" + getInstanceId() + ") is still " + 
                    "active but was recovered by another instance in the cluster.  " +
                    "This may cause inconsistent behavior.");
            }
            
            return failedInstances;
        } catch (Exception e) {
            lastCheckin = System.currentTimeMillis();
            throw new JobPersistenceException("Failure identifying failed instances when checking-in: "
                    + e.getMessage(), e);
        }
    }
