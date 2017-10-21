	@Test
	public void initializeOnce() throws Exception {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setServletContext(new MockServletContext());
		context.register(HandlerMappingConfiguration.class);
		context.refresh();

		ResourceUrlProvider urlProviderBean = context.getBean(ResourceUrlProvider.class);
		assertThat(urlProviderBean.getHandlerMap(), Matchers.hasKey(pattern("/resources/**")));
	}
