	@Before
	public void createFilter() throws Exception {
		VersionResourceResolver versionResolver = new VersionResourceResolver();
		versionResolver.setStrategyMap(Collections.singletonMap("/**", new ContentVersionStrategy()));
		PathResourceResolver pathResolver = new PathResourceResolver();
		pathResolver.setAllowedLocations(new ClassPathResource("test/", getClass()));
		List<ResourceResolver> resolvers = Arrays.asList(versionResolver, pathResolver);

		this.filter = new ResourceUrlEncodingFilter();
		this.resourceUrlProvider = createResourceUrlProvider(resolvers);
	}
