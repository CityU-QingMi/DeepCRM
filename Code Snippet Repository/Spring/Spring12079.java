	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.servletClass == null) {
			throw new IllegalArgumentException("'servletClass' is required");
		}
		if (this.servletName == null) {
			this.servletName = this.beanName;
		}
		this.servletInstance = ReflectionUtils.accessibleConstructor(this.servletClass).newInstance();
		this.servletInstance.init(new DelegatingServletConfig());
	}
