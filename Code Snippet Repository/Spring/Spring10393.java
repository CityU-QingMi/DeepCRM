	@Test
	public void ignoreAcceptHeader() throws Exception {
		this.factoryBean.setIgnoreAcceptHeader(true);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addHeader("Accept", MediaType.IMAGE_GIF_VALUE);

		assertEquals(Collections.<MediaType>emptyList(), manager.resolveMediaTypes(this.webRequest));
	}
