	@Before
	public void setup() {
		ClassPathResource allowedLocation = new ClassPathResource("test/", getClass());
		ResourceHttpRequestHandler resourceHandler = new ResourceHttpRequestHandler();
		ResourceUrlProvider resourceUrlProvider = new ResourceUrlProvider();
		resourceUrlProvider.setHandlerMap(Collections.singletonMap("/static/**", resourceHandler));

		VersionResourceResolver versionResolver = new VersionResourceResolver();
		versionResolver.setStrategyMap(Collections.singletonMap("/**", new ContentVersionStrategy()));
		PathResourceResolver pathResolver = new PathResourceResolver();
		pathResolver.setAllowedLocations(allowedLocation);
		List<ResourceResolver> resolvers = Arrays.asList(versionResolver, pathResolver);
		ResourceResolverChain resolverChain = new DefaultResourceResolverChain(resolvers);

		CssLinkResourceTransformer cssLinkResourceTransformer = new CssLinkResourceTransformer();
		cssLinkResourceTransformer.setResourceUrlProvider(resourceUrlProvider);
		List<ResourceTransformer> transformers = Arrays.asList(cssLinkResourceTransformer);
		this.chain = new DefaultResourceTransformerChain(resolverChain, transformers);
		this.transformer = new AppCacheManifestTransformer();
		this.transformer.setResourceUrlProvider(resourceUrlProvider);

		resourceHandler.setResourceResolvers(resolvers);
		resourceHandler.setResourceTransformers(transformers);
		resourceHandler.setLocations(Collections.singletonList(allowedLocation));
	}
