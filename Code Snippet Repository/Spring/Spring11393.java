	@Test
	public void resourceChainWithOverrides() throws Exception {
		CachingResourceResolver cachingResolver = Mockito.mock(CachingResourceResolver.class);
		VersionResourceResolver versionResolver = Mockito.mock(VersionResourceResolver.class);
		WebJarsResourceResolver webjarsResolver = Mockito.mock(WebJarsResourceResolver.class);
		PathResourceResolver pathResourceResolver = new PathResourceResolver();
		CachingResourceTransformer cachingTransformer = Mockito.mock(CachingResourceTransformer.class);
		AppCacheManifestTransformer appCacheTransformer = Mockito.mock(AppCacheManifestTransformer.class);
		CssLinkResourceTransformer cssLinkTransformer = new CssLinkResourceTransformer();

		this.registration.setCacheControl(CacheControl.maxAge(3600, TimeUnit.MILLISECONDS))
				.resourceChain(false)
					.addResolver(cachingResolver)
					.addResolver(versionResolver)
					.addResolver(webjarsResolver)
					.addResolver(pathResourceResolver)
					.addTransformer(cachingTransformer)
					.addTransformer(appCacheTransformer)
					.addTransformer(cssLinkTransformer);

		ResourceWebHandler handler = getHandler("/resources/**");
		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers.toString(), resolvers, Matchers.hasSize(4));
		assertThat(resolvers.get(0), Matchers.sameInstance(cachingResolver));
		assertThat(resolvers.get(1), Matchers.sameInstance(versionResolver));
		assertThat(resolvers.get(2), Matchers.sameInstance(webjarsResolver));
		assertThat(resolvers.get(3), Matchers.sameInstance(pathResourceResolver));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(3));
		assertThat(transformers.get(0), Matchers.sameInstance(cachingTransformer));
		assertThat(transformers.get(1), Matchers.sameInstance(appCacheTransformer));
		assertThat(transformers.get(2), Matchers.sameInstance(cssLinkTransformer));
	}
