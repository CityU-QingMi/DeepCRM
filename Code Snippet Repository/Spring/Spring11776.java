	@Test
	public void retrieveModelAttributeFromSession() throws Exception {
		WebSession session = this.exchange.getSession().block(Duration.ZERO);
		assertNotNull(session);

		TestBean testBean = new TestBean("Session Bean");
		session.getAttributes().put("bean", testBean);

		TestController controller = new TestController();
		InitBinderBindingContext context = getBindingContext(controller);

		Method method = ResolvableMethod.on(TestController.class).annotPresent(GetMapping.class).resolveMethod();
		HandlerMethod handlerMethod = new HandlerMethod(controller, method);
		this.modelInitializer.initModel(handlerMethod, context, this.exchange).block(Duration.ofMillis(5000));

		context.saveModel();
		assertEquals(1, session.getAttributes().size());
		assertEquals("Session Bean", ((TestBean) session.getRequiredAttribute("bean")).getName());
	}
