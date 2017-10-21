	@Test
	public void directlyAnnotatedWithImport() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(ImportingConfig.class);
		ctx.refresh();
		assertNotNull(ctx.getBean("importedConfigBean"));

		ImportedConfig importAwareConfig = ctx.getBean(ImportedConfig.class);
		AnnotationMetadata importMetadata = importAwareConfig.importMetadata;
		assertThat("import metadata was not injected", importMetadata, notNullValue());
		assertThat(importMetadata.getClassName(), is(ImportingConfig.class.getName()));
		AnnotationAttributes importAttribs = AnnotationConfigUtils.attributesFor(importMetadata, Import.class);
		Class<?>[] importedClasses = importAttribs.getClassArray("value");
		assertThat(importedClasses[0].getName(), is(ImportedConfig.class.getName()));
	}
