    @Test
    public void testLoad() 
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
            //make a root context
            ContextHandler handler  = new ContextHandler();
            handler.setContextPath("/");
            SessionContext sc = new SessionContext("0", handler.getServletContext());
            //test the load statement
            PreparedStatement s = _tableSchema.getLoadStatement(con, "1234", sc);
            ResultSet rs = s.executeQuery();
            assertTrue(rs.next());
        }
    }
