    @Test
    public void testUpdate()
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
            PreparedStatement s = _tableSchema.getUpdateStatement(con, 
                                                                  "1234",
                                                                  sc);

            s.setString(1, "0");//should be my node id
            s.setLong(2, System.currentTimeMillis());
            s.setLong(3, System.currentTimeMillis());
            s.setLong(4, System.currentTimeMillis());
            s.setLong(5, System.currentTimeMillis());
            s.setLong(6, 2000L);


            byte[] bytes = new byte[3];
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            s.setBinaryStream(7, bais, bytes.length);//attribute map as blob

            assertEquals(1, s.executeUpdate());
        }
        
    }
