    @Before
    public void onSetup() throws Exception
    {
        toolchainManager = new DefaultToolchainManager();

        MockitoAnnotations.initMocks( this );

        toolchainManager.factories = new HashMap<>();
        toolchainManager.factories.put( "basic", toolchainFactory_basicType );
        toolchainManager.factories.put( "rare", toolchainFactory_rareType );
    }
