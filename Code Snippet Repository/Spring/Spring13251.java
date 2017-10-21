	@Before
	public void setUp() throws Exception {

		List<Resource> paths = new ArrayList<>(2);
		paths.add(new ClassPathResource("test/", getClass()));
		paths.add(new ClassPathResource("testalternatepath/", getClass()));
		paths.add(new ClassPathResource("META-INF/resources/webjars/"));

		this.handler = new ResourceHttpRequestHandler();
		this.handler.setLocations(paths);
		this.handler.setCacheSeconds(3600);
		this.handler.setServletContext(new TestServletContext());
		this.handler.afterPropertiesSet();

		this.request = new MockHttpServletRequest("GET", "");
		this.response = new MockHttpServletResponse();
	}
