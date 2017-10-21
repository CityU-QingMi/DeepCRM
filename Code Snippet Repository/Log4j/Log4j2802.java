    @Test
    public void testWriteInternalNotConnected02() {
        given(connection.isClosed()).willReturn(true);

        try (final NoSqlDatabaseManager<?> manager = NoSqlDatabaseManager.getNoSqlDatabaseManager("name", 0,
            provider)) {

            manager.startup();
            manager.connectAndStart();
            then(provider).should().getConnection();

            expectedException.expect(AppenderLoggingException.class);
            manager.writeInternal(mock(LogEvent.class));
        }
    }
