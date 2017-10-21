	private List<ViewResolver> initViewResolvers(WebApplicationContext wac) {
		this.viewResolvers = (this.viewResolvers != null ? this.viewResolvers :
				Collections.<ViewResolver>singletonList(new InternalResourceViewResolver()));
		for (Object viewResolver : this.viewResolvers) {
			if (viewResolver instanceof WebApplicationObjectSupport) {
				((WebApplicationObjectSupport) viewResolver).setApplicationContext(wac);
			}
		}
		return this.viewResolvers;
	}
