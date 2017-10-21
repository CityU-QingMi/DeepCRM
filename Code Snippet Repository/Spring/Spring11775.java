	@Test
	public void saveModelAttributeToSession() throws Exception {
		TestController controller = new TestController();
		InitBinderBindingContext context = getBindingContext(controller);

		Method method = ResolvableMethod.on(TestController.class).annotPresent(GetMapping.class).resolveMethod();
		HandlerMethod handlerMethod = new HandlerMethod(controller, method);
		this.modelInitializer.initModel(handlerMethod, context, this.exchange).block(Duration.ofMillis(5000));

		WebSession session = this.exchange.getSession().block(Duration.ZERO);
		assertNotNull(session);
		assertEquals(0, session.getAttributes().size());

		context.saveModel();
		assertEquals(1, session.getAttributes().size());
		assertEquals("Bean", ((TestBean) session.getRequiredAttribute("bean")).getName());
	}
