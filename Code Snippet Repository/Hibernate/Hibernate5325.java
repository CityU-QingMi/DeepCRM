	@Before
	public void init(){
		Mockito.reset( scanEnvironment );

		when( options.getScanEnvironment() ).thenReturn( scanEnvironment );
		when( options.getServiceRegistry() ).thenReturn( serviceRegistry );
		when( serviceRegistry.getService( ClassLoaderService.class ) ).thenReturn( classLoaderService );

		when( scanEnvironment.getExplicitlyListedClassNames() ).thenReturn(
				Arrays.asList( "a.b.C" ) );

		when( classLoaderService.classForName( "a.b.C" ) ).thenReturn( Object.class );

		triggerable = logInspection.watchForLogMessages( "Unable" );
		triggerable.reset();
	}
