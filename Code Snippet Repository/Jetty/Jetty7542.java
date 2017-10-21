    @Before
    public void setUp() throws Exception
    {
        //pretend to be an Oracle-like database that treats "" as NULL
        _da = new DatabaseAdaptor()
        {

/**/
/**/
/**/
            @Override
            public boolean isEmptyStringNull()
            {
                return true; //test special handling for oracle
            }

        };
        _da.setDriverInfo(JdbcTestHelper.DRIVER_CLASS, JdbcTestHelper.DEFAULT_CONNECTION_URL);
        _tableSchema = JdbcTestHelper.newSessionTableSchema();
        _tableSchema.setDatabaseAdaptor(_da);
    }
