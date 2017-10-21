    protected List<OperableTrigger> getTriggersForJob(Connection conn,
            JobKey key)
        throws JobPersistenceException {
        List<OperableTrigger> list;

        try {
            list = getDelegate()
                    .selectTriggersForJob(conn, key);
        } catch (Exception e) {
            throw new JobPersistenceException(
                    "Couldn't obtain triggers for job: " + e.getMessage(), e);
        }

        return list;
    }
