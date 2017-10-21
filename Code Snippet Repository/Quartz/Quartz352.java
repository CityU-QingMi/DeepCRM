    private List<SchedulerStateRecord> findOrphanedFailedInstances(
            Connection conn, 
            List<SchedulerStateRecord> schedulerStateRecords) 
        throws SQLException, NoSuchDelegateException {
        List<SchedulerStateRecord> orphanedInstances = new ArrayList<SchedulerStateRecord>();
        
        Set<String> allFiredTriggerInstanceNames = getDelegate().selectFiredTriggerInstanceNames(conn);
        if (!allFiredTriggerInstanceNames.isEmpty()) {
            for (SchedulerStateRecord rec: schedulerStateRecords) {
                
                allFiredTriggerInstanceNames.remove(rec.getSchedulerInstanceId());
            }
            
            for (String inst: allFiredTriggerInstanceNames) {
                
                SchedulerStateRecord orphanedInstance = new SchedulerStateRecord();
                orphanedInstance.setSchedulerInstanceId(inst);
                
                orphanedInstances.add(orphanedInstance);
                
                getLog().warn(
                    "Found orphaned fired triggers for instance: " + orphanedInstance.getSchedulerInstanceId());
            }
        }
        
        return orphanedInstances;
    }
