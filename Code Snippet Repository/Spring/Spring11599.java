	@Before
	public void setup() throws Exception {
		List<Resource> paths = new ArrayList<>(2);
		paths.add(new ClassPathResource("test/", getClass()));
		paths.add(new ClassPathResource("testalternatepath/", getClass()));
		paths.add(new ClassPathResource("META-INF/resources/webjars/"));

		this.handler = new ResourceWebHandler();
		this.handler.setLocations(paths);
		this.handler.setCacheControl(CacheControl.maxAge(3600, TimeUnit.SECONDS));
		this.handler.afterPropertiesSet();
	}
