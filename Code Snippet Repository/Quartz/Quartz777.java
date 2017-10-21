    public void testSelectSimpleTriggerWithExceptionWithExtendedProps() throws SQLException, JobPersistenceException, IOException, ClassNotFoundException {
        TriggerPersistenceDelegate persistenceDelegate = mock(TriggerPersistenceDelegate.class);
        IllegalStateException exception = new IllegalStateException();
        when(persistenceDelegate.loadExtendedTriggerProperties(any(Connection.class), any(TriggerKey.class))).thenThrow(exception);

        StdJDBCDelegate jdbcDelegate = new TestStdJDBCDelegate(persistenceDelegate);
        jdbcDelegate.initialize(LoggerFactory.getLogger(getClass()), "QRTZ_", "TESTSCHED", "INSTANCE", new SimpleClassLoadHelper(), false, "");

        Connection conn = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(preparedStatement);

        // Mock basic trigger data
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getString(Constants.COL_TRIGGER_TYPE)).thenReturn(Constants.TTYPE_SIMPLE);

        try {
            jdbcDelegate.selectTrigger(conn, TriggerKey.triggerKey("test"));
            fail("Trigger selection should result in exception");
        } catch (IllegalStateException e) {
            assertSame(exception, e);
        }
        verify(persistenceDelegate).loadExtendedTriggerProperties(any(Connection.class), any(TriggerKey.class));

    }
