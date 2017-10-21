    @Test
    public void testDelete()
    throws Exception
    {
        //set up the db
        _da.initialize();
        _tableSchema.prepareTables();
        
        //insert a fake session at the root context
        JdbcTestHelper.insertSession("1234", "/", "0.0.0.0");
        
        //test if it can be deleted
        try (Connection con = _da.getConnection())
        {
            ContextHandler handler  = new ContextHandler();
            handler.setContextPath("/");
            SessionContext sc = new SessionContext("0", handler.getServletContext());
            PreparedStatement s = _tableSchema.getDeleteStatement(con, "1234", sc);
            assertEquals(1,s.executeUpdate());
            
            assertFalse(JdbcTestHelper.existsInSessionTable("1234", false));
        }
    }
