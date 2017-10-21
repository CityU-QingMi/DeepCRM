	@Test
	public void setDefaultContentType() throws Exception {
		this.factoryBean.setDefaultContentType(MediaType.APPLICATION_JSON);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		assertEquals(MediaType.APPLICATION_JSON, manager.resolveMediaTypes(this.webRequest).get(0));

		// SPR-10513
		this.servletRequest.addHeader("Accept", MediaType.ALL_VALUE);
		assertEquals(MediaType.APPLICATION_JSON, manager.resolveMediaTypes(this.webRequest).get(0));
	}
