	@Test
	public void customTemplateConfiguration() throws Exception {
		this.configurer.setApplicationContext(this.applicationContext);
		this.configurer.setCacheTemplates(false);
		this.configurer.afterPropertiesSet();

		TemplateEngine engine = this.configurer.getTemplateEngine();
		assertNotNull(engine);
		assertEquals(MarkupTemplateEngine.class, engine.getClass());

		MarkupTemplateEngine markupEngine = (MarkupTemplateEngine) engine;
		TemplateConfiguration configuration = markupEngine.getTemplateConfiguration();
		assertNotNull(configuration);
		assertFalse(configuration.isCacheTemplates());
	}
