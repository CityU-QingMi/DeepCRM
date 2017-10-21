	@Test
	public void favorParameter() throws Exception {
		this.factoryBean.setFavorParameter(true);

		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		this.factoryBean.addMediaTypes(mediaTypes);

		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.addParameter("format", "json");

		assertEquals(Collections.singletonList(MediaType.APPLICATION_JSON),
				manager.resolveMediaTypes(this.webRequest));
	}
