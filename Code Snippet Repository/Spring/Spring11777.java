	@Test
	public void requiredSessionAttributeMissing() throws Exception {
		TestController controller = new TestController();
		InitBinderBindingContext context = getBindingContext(controller);

		Method method = ResolvableMethod.on(TestController.class).annotPresent(PostMapping.class).resolveMethod();
		HandlerMethod handlerMethod = new HandlerMethod(controller, method);
		try {
			this.modelInitializer.initModel(handlerMethod, context, this.exchange).block(Duration.ofMillis(5000));
			fail();
		}
		catch (IllegalArgumentException ex) {
			assertEquals("Required attribute 'missing-bean' is missing.", ex.getMessage());
		}
	}
