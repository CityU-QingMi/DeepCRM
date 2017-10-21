    @SuppressWarnings("")
    public List<TriggerFiredResult> triggersFired(final List<OperableTrigger> triggers) throws JobPersistenceException {
        return executeInNonManagedTXLock(LOCK_TRIGGER_ACCESS,
                new TransactionCallback<List<TriggerFiredResult>>() {
                    public List<TriggerFiredResult> execute(Connection conn) throws JobPersistenceException {
                        List<TriggerFiredResult> results = new ArrayList<TriggerFiredResult>();

                        TriggerFiredResult result;
                        for (OperableTrigger trigger : triggers) {
                            try {
                              TriggerFiredBundle bundle = triggerFired(conn, trigger);
                              result = new TriggerFiredResult(bundle);
                            } catch (JobPersistenceException jpe) {
                                result = new TriggerFiredResult(jpe);
                            } catch(RuntimeException re) {
                                result = new TriggerFiredResult(re);
                            }
                            results.add(result);
                        }

                        return results;
                    }
                },
                new TransactionValidator<List<TriggerFiredResult>>() {
                    @Override
                    public Boolean validate(Connection conn, List<TriggerFiredResult> result) throws JobPersistenceException {
                        try {
                            List<FiredTriggerRecord> acquired = getDelegate().selectInstancesFiredTriggerRecords(conn, getInstanceId());
                            Set<String> executingTriggers = new HashSet<String>();
                            for (FiredTriggerRecord ft : acquired) {
                                if (STATE_EXECUTING.equals(ft.getFireInstanceState())) {
                                    executingTriggers.add(ft.getFireInstanceId());
                                }
                            }
                            for (TriggerFiredResult tr : result) {
                                if (tr.getTriggerFiredBundle() != null && executingTriggers.contains(tr.getTriggerFiredBundle().getTrigger().getFireInstanceId())) {
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
