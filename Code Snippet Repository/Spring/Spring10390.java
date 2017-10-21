	@Test(expected = HttpMediaTypeNotAcceptableException.class)
	public void favorPathWithIgnoreUnknownPathExtensionTurnedOff() throws Exception {
		this.factoryBean.setFavorPathExtension(true);
		this.factoryBean.setIgnoreUnknownPathExtensions(false);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		this.servletRequest.setRequestURI("/flower.foobarbaz");
		this.servletRequest.addParameter("format", "json");

		manager.resolveMediaTypes(this.webRequest);
	}
