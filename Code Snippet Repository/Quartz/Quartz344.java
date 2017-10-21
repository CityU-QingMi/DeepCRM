    @SuppressWarnings("")
    public List<OperableTrigger> acquireNextTriggers(final long noLaterThan, final int maxCount, final long timeWindow)
        throws JobPersistenceException {
        
        String lockName;
        if(isAcquireTriggersWithinLock() || maxCount > 1) { 
            lockName = LOCK_TRIGGER_ACCESS;
        } else {
            lockName = null;
        }
        return executeInNonManagedTXLock(lockName, 
                new TransactionCallback<List<OperableTrigger>>() {
                    public List<OperableTrigger> execute(Connection conn) throws JobPersistenceException {
                        return acquireNextTrigger(conn, noLaterThan, maxCount, timeWindow);
                    }
                },
                new TransactionValidator<List<OperableTrigger>>() {
                    public Boolean validate(Connection conn, List<OperableTrigger> result) throws JobPersistenceException {
                        try {
                            List<FiredTriggerRecord> acquired = getDelegate().selectInstancesFiredTriggerRecords(conn, getInstanceId());
                            Set<String> fireInstanceIds = new HashSet<String>();
                            for (FiredTriggerRecord ft : acquired) {
                                fireInstanceIds.add(ft.getFireInstanceId());
                            }
                            for (OperableTrigger tr : result) {
                                if (fireInstanceIds.contains(tr.getFireInstanceId())) {
                                    return true;
                                }
                            }
                            return false;
                        } catch (SQLException e) {
                            throw new JobPersistenceException("error validating trigger acquisition", e);
                        }
                    }
                });
    }
