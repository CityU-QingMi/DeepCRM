	@Test
	public void customTemplateEngine() throws Exception {
		this.configurer.setApplicationContext(this.applicationContext);
		this.configurer.setTemplateEngine(new TestTemplateEngine());
		this.configurer.afterPropertiesSet();

		TemplateEngine engine = this.configurer.getTemplateEngine();
		assertNotNull(engine);
		assertEquals(TestTemplateEngine.class, engine.getClass());
	}
