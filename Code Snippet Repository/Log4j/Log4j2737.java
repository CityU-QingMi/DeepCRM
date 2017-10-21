    @Test
    public void testDriverManager_setLogWriter() throws SQLException {
        DriverManager.setLogWriter(createLoggerPrintWriter());
        // DriverManager.setLogWriter(new PrintWriter(new OutputStreamWriter(System.out)));
        try (final Connection conn = this.newConnection()) {
            conn.rollback();
        } finally {
            DriverManager.setLogWriter(null);
        }
        Assert.assertTrue(this.getListAppender().getMessages().size() > 0);
    }
