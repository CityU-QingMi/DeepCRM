	private ApplicationContext initApplicationContext() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		this.controllers.forEach(controller -> {
			String name = controller.getClass().getName();
			context.registerBean(name, Object.class, () -> controller);
		});
		this.controllerAdvice.forEach(advice -> {
			String name = advice.getClass().getName();
			context.registerBean(name, Object.class, () -> advice);
		});
		context.register(DelegatingWebFluxConfiguration.class);
		context.registerBean(WebFluxConfigurer.class, () -> this.configurer);
		context.refresh();
		return context;
	}
