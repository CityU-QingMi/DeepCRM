    @Test
    @Ignore("")
    public void testDataSource_setLogWriter() throws SQLException {
        final JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl(H2_URL);
        dataSource.setUser(USER_ID);
        dataSource.setPassword(PASSWORD);
        dataSource.setLogWriter(createLoggerPrintWriter());
        // dataSource.setLogWriter(new PrintWriter(new OutputStreamWriter(System.out)));
        try (final Connection conn = dataSource.getConnection()) {
            conn.prepareCall("select 1");
        }
        Assert.assertTrue(this.getListAppender().getMessages().size() > 0);
    }
