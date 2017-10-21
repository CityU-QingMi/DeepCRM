	@Test(expected = HttpMediaTypeNotAcceptableException.class)
	public void favorParameterWithUnknownMediaType() throws HttpMediaTypeNotAcceptableException {
		this.factoryBean.setFavorParameter(true);
		this.factoryBean.afterPropertiesSet();
		ContentNegotiationManager manager = this.factoryBean.getObject();

		this.servletRequest.setRequestURI("/flower");
		this.servletRequest.setParameter("format", "invalid");

		manager.resolveMediaTypes(this.webRequest);
	}
