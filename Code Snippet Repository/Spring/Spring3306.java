	@Test
	public void importRegistrarWithImport() throws Exception {
		ImportedRegistrar.called = false;
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ImportingRegistrarConfigWithImport.class);
		ctx.refresh();
		assertNotNull(ctx.getBean("registrarImportedBean"));
		assertNotNull(ctx.getBean("otherImportedConfigBean"));
		assertNotNull(ctx.getBean("importedConfigBean"));
		assertNotNull(ctx.getBean(ImportedConfig.class));
	}
