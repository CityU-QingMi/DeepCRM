    protected JobDetail retrieveJob(Connection conn, JobKey key) throws JobPersistenceException {
        try {

            return getDelegate().selectJobDetail(conn, key,
                    getClassLoadHelper());
        } catch (ClassNotFoundException e) {
            throw new JobPersistenceException(
                    "Couldn't retrieve job because a required class was not found: "
                            + e.getMessage(), e);
        } catch (IOException e) {
            throw new JobPersistenceException(
                    "Couldn't retrieve job because the BLOB couldn't be deserialized: "
                            + e.getMessage(), e);
        } catch (SQLException e) {
            throw new JobPersistenceException("Couldn't retrieve job: "
                    + e.getMessage(), e);
        }
    }
