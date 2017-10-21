    @Test
    @TestForIssue(jiraKey = "")
    public void testStoppableClassLoaderService() {
    	final BootstrapServiceRegistryBuilder bootstrapBuilder = new BootstrapServiceRegistryBuilder();
    	bootstrapBuilder.applyClassLoader( new TestClassLoader() );
    	final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder( bootstrapBuilder.build() ).build();
    	final ClassLoaderService classLoaderService = serviceRegistry.getService( ClassLoaderService.class );
    	
    	TestIntegrator testIntegrator1 = findTestIntegrator( classLoaderService );
    	assertNotNull( testIntegrator1 );
    	
    	TestIntegrator testIntegrator2 = findTestIntegrator( classLoaderService );
    	assertNotNull( testIntegrator2 );
    	
    	assertSame( testIntegrator1, testIntegrator2 );
    	
    	StandardServiceRegistryBuilder.destroy( serviceRegistry );
    	
    	try {
    		findTestIntegrator( classLoaderService );
    		Assert.fail("Should have thrown an HibernateException -- the ClassLoaderService instance was closed.");
    	}
    	catch (HibernateException e) {
    		String message = e.getMessage();
    		Assert.assertEquals( "HHH000469: The ClassLoaderService can not be reused. This instance was stopped already.", message);
    	}
    }
