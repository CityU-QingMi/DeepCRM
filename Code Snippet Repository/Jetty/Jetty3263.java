    @Before
    public void before()
    {
        super.before();
        if (_httpConfiguration!=null)
        {
            _httpConfiguration.setBlockingTimeout(-1L);
            _httpConfiguration.setMinRequestDataRate(-1);
            _httpConfiguration.setIdleTimeout(-1);
        }
        
    }
