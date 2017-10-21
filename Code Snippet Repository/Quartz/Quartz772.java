    @Override
    protected void destroyJobStore(String name) {
        try {
        	JobStoreSupport jdbcJobStore = stores.remove(name);
        	jdbcJobStore.shutdown();
        	
            JdbcQuartzTestUtilities.destroyDatabase(name);
        } catch (SQLException e) {
            throw new AssertionError(e);
        }
    }
