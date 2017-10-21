	@Test
	public void resourceChain() throws Exception {
		ResourceResolver mockResolver = Mockito.mock(ResourceResolver.class);
		ResourceTransformer mockTransformer = Mockito.mock(ResourceTransformer.class);
		this.registration.resourceChain(true).addResolver(mockResolver).addTransformer(mockTransformer);

		ResourceWebHandler handler = getHandler("/resources/**");
		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers.toString(), resolvers, Matchers.hasSize(4));
		assertThat(resolvers.get(0), Matchers.instanceOf(CachingResourceResolver.class));
		CachingResourceResolver cachingResolver = (CachingResourceResolver) resolvers.get(0);
		assertThat(cachingResolver.getCache(), Matchers.instanceOf(ConcurrentMapCache.class));
		assertThat(resolvers.get(1), Matchers.equalTo(mockResolver));
		assertThat(resolvers.get(2), Matchers.instanceOf(WebJarsResourceResolver.class));
		assertThat(resolvers.get(3), Matchers.instanceOf(PathResourceResolver.class));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(2));
		assertThat(transformers.get(0), Matchers.instanceOf(CachingResourceTransformer.class));
		assertThat(transformers.get(1), Matchers.equalTo(mockTransformer));
	}
