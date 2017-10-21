	@Override
	public void afterPropertiesSet() throws Exception {
		super.afterPropertiesSet();

		ServletContext servletContext = getServletContext();
		Assert.state(servletContext != null, "No ServletContext");
		this.applicationContext = ServletUtil.getApplicationContext(servletContext);

		if (this.renderer == null) {
			TilesContainer container = TilesAccess.getContainer(this.applicationContext);
			this.renderer = new DefinitionRenderer(container);
		}
	}
