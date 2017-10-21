	public UrlBasedViewResolverRegistration groovy() {
		if (!checkBeanOfType(GroovyMarkupConfigurer.class)) {
			throw new BeanInitializationException("In addition to a Groovy markup view resolver " +
					"there must also be a single GroovyMarkupConfig bean in this web application context " +
					"(or its parent): GroovyMarkupConfigurer is the usual implementation. " +
					"This bean may be given any name.");
		}
		GroovyMarkupRegistration registration = new GroovyMarkupRegistration();
		this.viewResolvers.add(registration.getViewResolver());
		return registration;
	}
