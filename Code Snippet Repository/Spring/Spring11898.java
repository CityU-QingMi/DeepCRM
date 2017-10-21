	@Test
	public void missingTemplate() throws Exception {
		this.context.refresh();
		this.view.setResourceLoaderPath("classpath:org/springframework/web/reactive/result/view/script/");
		this.view.setUrl("missing.txt");
		this.view.setEngine(mock(InvocableScriptEngine.class));
		this.configurer.setRenderFunction("render");
		this.view.setApplicationContext(this.context);
		assertFalse(this.view.checkResourceExists(Locale.ENGLISH));
	}
