	protected void setUp() throws Exception
	{
		super.setUp();
		//userDir = System.getProperty("user.dir");
		
		// load base ApplicationResources.properties file
		baseProps = new Properties();
		baseProps.load(new FileInputStream( 
                System.getProperty("project.build.directory") + "/classes/ApplicationResources.properties"));
	}
