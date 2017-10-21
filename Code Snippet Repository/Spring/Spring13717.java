	@Test
	public void detectTemplateEngine() throws Exception {
		GroovyMarkupView view = new GroovyMarkupView();
		view.setTemplateEngine(new TestTemplateEngine());
		view.setApplicationContext(this.webAppContext);

		DirectFieldAccessor accessor = new DirectFieldAccessor(view);
		TemplateEngine engine = (TemplateEngine) accessor.getPropertyValue("engine");
		assertNotNull(engine);
		assertEquals(TestTemplateEngine.class, engine.getClass());
	}
