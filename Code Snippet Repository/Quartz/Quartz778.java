    public void testSelectSimpleTriggerWithDeleteBeforeSelectExtendedProps() throws JobPersistenceException, ClassNotFoundException, SQLException, IOException {
        TriggerPersistenceDelegate persistenceDelegate = mock(TriggerPersistenceDelegate.class);
        when(persistenceDelegate.loadExtendedTriggerProperties(any(Connection.class), any(TriggerKey.class))).thenThrow(new IllegalStateException());

        StdJDBCDelegate jdbcDelegate = new TestStdJDBCDelegate(persistenceDelegate);
        jdbcDelegate.initialize(LoggerFactory.getLogger(getClass()), "QRTZ_", "TESTSCHED", "INSTANCE", new SimpleClassLoadHelper(), false, "");

        Connection conn = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        ResultSet resultSet = mock(ResultSet.class);

        when(conn.prepareStatement(anyString())).thenReturn(preparedStatement);

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        // First result set has results, second has none
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString(Constants.COL_TRIGGER_TYPE)).thenReturn(Constants.TTYPE_SIMPLE);

        OperableTrigger trigger = jdbcDelegate.selectTrigger(conn, TriggerKey.triggerKey("test"));
        assertNull(trigger);
        verify(persistenceDelegate).loadExtendedTriggerProperties(any(Connection.class), any(TriggerKey.class));
    }
