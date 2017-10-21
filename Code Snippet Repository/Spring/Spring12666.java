	@Test
	public void contentNegotiationAddsDefaultViewRegistrations() {
		MappingJackson2JsonView view1 = new MappingJackson2JsonView();
		this.registry.enableContentNegotiation(view1);

		ContentNegotiatingViewResolver resolver1 = checkAndGetResolver(ContentNegotiatingViewResolver.class);
		assertEquals(Arrays.asList(view1), resolver1.getDefaultViews());

		MarshallingView view2 = new MarshallingView();
		this.registry.enableContentNegotiation(view2);

		ContentNegotiatingViewResolver resolver2 = checkAndGetResolver(ContentNegotiatingViewResolver.class);
		assertEquals(Arrays.asList(view1, view2), resolver2.getDefaultViews());
		assertSame(resolver1, resolver2);
	}
