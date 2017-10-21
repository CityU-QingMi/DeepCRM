    @Test
    public void testConnection() {
        try (final NoSqlDatabaseManager<?> manager = NoSqlDatabaseManager.getNoSqlDatabaseManager("name", 0,
            provider)) {

            assertNotNull("The manager should not be null.", manager);

            manager.connectAndStart();
            then(provider).should().getConnection();
            manager.commitAndClose();
        }
    }
