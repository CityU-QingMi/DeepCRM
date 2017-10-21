    @Test
    public void testExpired()
    throws Exception
    {
        //set up the db
        _da.initialize();
        _tableSchema.prepareTables();
        
        //insert a fake session at the root context
        JdbcTestHelper.insertSession("1234", "/", "0.0.0.0");
        
    
        try (Connection con = _da.getConnection())
        {
            ContextHandler handler  = new ContextHandler();
            handler.setContextPath("/");
            SessionContext sc = new SessionContext("0", handler.getServletContext());
            PreparedStatement s = _tableSchema.getExpiredSessionsStatement(con, 
                                                                           sc.getCanonicalContextPath(), 
                                                                           sc.getVhost(), 
                                                                           (System.currentTimeMillis()+100L));
            ResultSet rs = s.executeQuery();
            assertTrue(rs.next());
            assertEquals("1234", rs.getString(1));
        }
    }
