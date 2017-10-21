    @Override
    public org.junit.runners.model.Statement apply(final org.junit.runners.model.Statement base,
                                                   final Description description) {
        return new org.junit.runners.model.Statement() {
            @Override
            public void evaluate() throws Throwable {
                try (final Connection connection = connectionSource.getConnection()) {
                    try (final Statement statement = connection.createStatement()) {
                        statement.executeUpdate(createTableStatement);
                    }
                    base.evaluate();
                    try (final Statement statement = connection.createStatement()) {
                        statement.executeUpdate(dropTableStatement);
                        statement.execute("SHUTDOWN");
                    }
                }
            }
        };
    }
