	@Test
	public void buildMergedConfigWithAtWebAppConfigurationWithAnnotationAndClassesOnSuperclass() {
		Class<?> webTestClass = WebClassesFoo.class;
		Class<?> standardTestClass = ClassesFoo.class;
		WebMergedContextConfiguration webMergedConfig = (WebMergedContextConfiguration) buildMergedContextConfiguration(webTestClass);
		MergedContextConfiguration standardMergedConfig = buildMergedContextConfiguration(standardTestClass);

		assertEquals(webMergedConfig, webMergedConfig);
		assertEquals(standardMergedConfig, standardMergedConfig);
		assertNotEquals(standardMergedConfig, webMergedConfig);
		assertNotEquals(webMergedConfig, standardMergedConfig);

		assertMergedConfig(webMergedConfig, webTestClass, EMPTY_STRING_ARRAY, array(FooConfig.class),
			WebDelegatingSmartContextLoader.class);
		assertMergedConfig(standardMergedConfig, standardTestClass, EMPTY_STRING_ARRAY,
			array(FooConfig.class), DelegatingSmartContextLoader.class);
	}
