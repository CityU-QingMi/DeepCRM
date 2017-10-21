    protected List<SchedulerStateRecord> clusterCheckIn(Connection conn)
        throws JobPersistenceException {

        List<SchedulerStateRecord> failedInstances = findFailedInstances(conn);
        
        try {
            // FUTURE_TODO: handle self-failed-out

            // check in...
            lastCheckin = System.currentTimeMillis();
            if(getDelegate().updateSchedulerState(conn, getInstanceId(), lastCheckin) == 0) {
                getDelegate().insertSchedulerState(conn, getInstanceId(),
                        lastCheckin, getClusterCheckinInterval());
            }
            
        } catch (Exception e) {
            throw new JobPersistenceException("Failure updating scheduler state when checking-in: "
                    + e.getMessage(), e);
        }

        return failedInstances;
    }
