	public UrlBasedViewResolverRegistration freeMarker() {
		if (!checkBeanOfType(FreeMarkerConfigurer.class)) {
			throw new BeanInitializationException("In addition to a FreeMarker view resolver " +
					"there must also be a single FreeMarkerConfig bean in this web application context " +
					"(or its parent): FreeMarkerConfigurer is the usual implementation. " +
					"This bean may be given any name.");
		}
		FreeMarkerRegistration registration = new FreeMarkerRegistration();
		UrlBasedViewResolver resolver = registration.getViewResolver();
		if (this.applicationContext != null) {
			resolver.setApplicationContext(this.applicationContext);
		}
		this.viewResolvers.add(resolver);
		return registration;
	}
