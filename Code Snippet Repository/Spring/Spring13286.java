	@Before
	public void setUp() throws Exception {
		this.locations.add(new ClassPathResource("test/", getClass()));
		this.locations.add(new ClassPathResource("testalternatepath/", getClass()));
		this.handler.setServletContext(new MockServletContext());
		this.handler.setLocations(locations);
		this.handler.afterPropertiesSet();
		this.handlerMap.put("/resources/**", this.handler);
		this.urlProvider.setHandlerMap(this.handlerMap);
	}
