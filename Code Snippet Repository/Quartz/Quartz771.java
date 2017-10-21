    @Override
    protected JobStore createJobStore(String name) {
        try {
            JdbcQuartzTestUtilities.createDatabase(name);
            JobStoreTX jdbcJobStore = new JobStoreTX();
            jdbcJobStore.setDataSource(name);
            jdbcJobStore.setTablePrefix("QRTZ_");
            jdbcJobStore.setInstanceId("SINGLE_NODE_TEST");
            jdbcJobStore.setInstanceName(name);
            jdbcJobStore.setUseDBLocks(true);

            stores.put(name, jdbcJobStore);
            
            return jdbcJobStore;
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }
