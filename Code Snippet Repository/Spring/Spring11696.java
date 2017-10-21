	@Test
	public void modelAttributeAdvice() throws Exception {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		RequestMappingHandlerAdapter adapter = createAdapter(context);
		TestController controller = context.getBean(TestController.class);

		Model model = handle(adapter, controller, "handle").getModel();

		assertEquals(2, model.asMap().size());
		assertEquals("lAttr1", model.asMap().get("attr1"));
		assertEquals("gAttr2", model.asMap().get("attr2"));
	}
