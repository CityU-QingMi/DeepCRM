    @BeforeClass
    public static void setUp() throws Exception
    {
       
        _docRoot = MavenTestingUtils.getTargetTestingDir("loginservice-test");
        FS.ensureDirExists(_docRoot);
        
        File content = new File(_docRoot,"input.txt");
        FileOutputStream out = new FileOutputStream(content);
        out.write(_content.getBytes("utf-8"));
        out.close();

        
        //clear previous runs
        File scriptFile = MavenTestingUtils.getTestResourceFile("droptables.sql");
        int result = DatabaseLoginServiceTestServer.runscript(scriptFile);
        //ignore result as derby spits errors for dropping tables that dont exist
        
        //create afresh
        scriptFile = MavenTestingUtils.getTestResourceFile("createdb.sql");
        result = DatabaseLoginServiceTestServer.runscript(scriptFile);
         assertThat("runScript result",result, is(0));
         
        _testServer = new DatabaseLoginServiceTestServer();
        _testServer.setResourceBase(_docRoot.getAbsolutePath());
        _testServer.setLoginService(configureLoginService());
        _testServer.start();
        _baseUri = _testServer.getBaseUri();
     }
