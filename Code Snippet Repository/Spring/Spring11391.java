	@Test
	public void resourceChainWithoutCaching() throws Exception {
		this.registration.resourceChain(false);

		ResourceWebHandler handler = getHandler("/resources/**");
		List<ResourceResolver> resolvers = handler.getResourceResolvers();
		assertThat(resolvers, Matchers.hasSize(2));
		assertThat(resolvers.get(0), Matchers.instanceOf(WebJarsResourceResolver.class));
		assertThat(resolvers.get(1), Matchers.instanceOf(PathResourceResolver.class));

		List<ResourceTransformer> transformers = handler.getResourceTransformers();
		assertThat(transformers, Matchers.hasSize(0));
	}
