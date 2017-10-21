	@Before
	public void init() {
		sessionFactoryImplementorMock = Mockito.mock(SessionFactoryImplementor.class);
		sessionFactoryOptionsMock = Mockito.mock(SessionFactoryOptions.class);
		when(sessionFactoryImplementorMock.getSessionFactoryOptions()).thenReturn( sessionFactoryOptionsMock );

		serviceRegistryMock = Mockito.mock(ServiceRegistryImplementor.class);
		when( sessionFactoryImplementorMock.getServiceRegistry() ).thenReturn( serviceRegistryMock );

		classLoaderServiceMock = Mockito.mock(ClassLoaderService.class);
		when( serviceRegistryMock.getService( eq( ClassLoaderService.class ) ) ).thenReturn( classLoaderServiceMock );
	}
