	@Test
	public void resourceChainWithVersionResolver() throws Exception {
		VersionResourceResolver versionResolver = new VersionResourceResolver()
				.addFixedVersionStrategy("fixed", "/**/*.js")
				.addContentVersionStrategy("/**");

		this.registration.resourceChain(true).addResolver(versionResolver)
				.addTransformer(new AppCacheManifestTransformer());

		ResourceHttpRequestHandler handler = getHandler("/resources/**");
		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers.toString(), resolvers, Matchers.hasSize(4));
		assertThat(resolvers.get(0), Matchers.instanceOf(CachingResourceResolver.class));
		assertThat(resolvers.get(1), Matchers.sameInstance(versionResolver));
		assertThat(resolvers.get(2), Matchers.instanceOf(WebJarsResourceResolver.class));
		assertThat(resolvers.get(3), Matchers.instanceOf(PathResourceResolver.class));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(3));
		assertThat(transformers.get(0), Matchers.instanceOf(CachingResourceTransformer.class));
		assertThat(transformers.get(1), Matchers.instanceOf(CssLinkResourceTransformer.class));
		assertThat(transformers.get(2), Matchers.instanceOf(AppCacheManifestTransformer.class));
	}
