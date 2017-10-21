	@Test
	public void setDefaultContentTypes() throws Exception {
		List<MediaType> mediaTypes = Arrays.asList(MediaType.APPLICATION_JSON, MediaType.ALL);
		this.factoryBean.setDefaultContentTypes(mediaTypes);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		assertEquals(mediaTypes, manager.resolveMediaTypes(this.webRequest));

		this.servletRequest.addHeader("Accept", MediaType.ALL_VALUE);
		assertEquals(mediaTypes, manager.resolveMediaTypes(this.webRequest));
	}
