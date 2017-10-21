	@Test
	public void defaultTemplateEngine() throws Exception {
		this.configurer.setApplicationContext(this.applicationContext);
		this.configurer.afterPropertiesSet();

		TemplateEngine engine = this.configurer.getTemplateEngine();
		assertNotNull(engine);
		assertEquals(MarkupTemplateEngine.class, engine.getClass());

		MarkupTemplateEngine markupEngine = (MarkupTemplateEngine) engine;
		TemplateConfiguration configuration = markupEngine.getTemplateConfiguration();
		assertNotNull(configuration);
		assertEquals(GroovyMarkupConfigurer.class, configuration.getClass());
	}
