    @Test
    public void testExists()
    throws Exception
    {
        //set up the db
        _da.initialize();
        _tableSchema.prepareTables();
        
        //insert a fake session at the root context
        JdbcTestHelper.insertSession("1234", "/", "0.0.0.0");
        
        //test if it can be seen
        try (Connection con = _da.getConnection())
        {
            ContextHandler handler  = new ContextHandler();
            handler.setContextPath("/");
            SessionContext sc = new SessionContext("0", handler.getServletContext());
            PreparedStatement s = _tableSchema.getCheckSessionExistsStatement(con, sc);
            s.setString(1,  "1234");
            ResultSet rs = s.executeQuery();
            assertTrue(rs.next());
        }
    }
