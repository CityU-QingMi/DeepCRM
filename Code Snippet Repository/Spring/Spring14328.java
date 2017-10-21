	@Test
	public void fileSystemXmlApplicationContext() throws IOException {
		ClassPathResource xml = new ClassPathResource(XML_PATH);
		File tmpFile = File.createTempFile("test", "xml");
		FileCopyUtils.copy(xml.getFile(), tmpFile);

		// strange - FSXAC strips leading '/' unless prefixed with 'file:'
		ConfigurableApplicationContext ctx =
				new FileSystemXmlApplicationContext(new String[] {"file:" + tmpFile.getPath()}, false);
		ctx.setEnvironment(prodEnv);
		ctx.refresh();
		assertEnvironmentBeanRegistered(ctx);
		assertHasEnvironment(ctx, prodEnv);
		assertEnvironmentAwareInvoked(ctx, ctx.getEnvironment());
		assertThat(ctx.containsBean(DEV_BEAN_NAME), is(false));
		assertThat(ctx.containsBean(PROD_BEAN_NAME), is(true));
	}
