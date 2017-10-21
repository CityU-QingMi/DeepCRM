	@Test
	public void indirectlyAnnotatedWithImport() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(IndirectlyImportingConfig.class);
		ctx.refresh();
		assertNotNull(ctx.getBean("importedConfigBean"));

		ImportedConfig importAwareConfig = ctx.getBean(ImportedConfig.class);
		AnnotationMetadata importMetadata = importAwareConfig.importMetadata;
		assertThat("import metadata was not injected", importMetadata, notNullValue());
		assertThat(importMetadata.getClassName(), is(IndirectlyImportingConfig.class.getName()));
		AnnotationAttributes enableAttribs = AnnotationConfigUtils.attributesFor(importMetadata, EnableImportedConfig.class);
		String foo = enableAttribs.getString("foo");
		assertThat(foo, is("xyz"));
	}
