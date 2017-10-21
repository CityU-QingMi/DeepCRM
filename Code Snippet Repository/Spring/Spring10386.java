	@Test
	public void setDefaultContentTypeWithStrategy() throws Exception {
		this.factoryBean.setDefaultContentTypeStrategy(new FixedContentNegotiationStrategy(MediaType.APPLICATION_JSON));
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON),
				manager.resolveMediaTypes(this.webRequest));

		this.servletRequest.addHeader("Accept", MediaType.ALL_VALUE);
		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON),
				manager.resolveMediaTypes(this.webRequest));
	}
