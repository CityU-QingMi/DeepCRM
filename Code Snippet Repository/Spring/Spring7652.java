	@Before
	public void setup() {
		TestSimpUser simpUser = new TestSimpUser("joe");
		simpUser.addSessions(new TestSimpSession("123"));

		this.registry = mock(SimpUserRegistry.class);
		when(this.registry.getUser("joe")).thenReturn(simpUser);

		this.resolver = new DefaultUserDestinationResolver(this.registry);
	}
