	@Test
	public void detectDefaultConfigurationClassesForAnnotatedInnerClass() {
		Class<?>[] configClasses = contextLoader.detectDefaultConfigurationClasses(ContextConfigurationInnerClassTestCase.class);
		assertNotNull(configClasses);
		assertEquals("annotated static ContextConfiguration should be considered.", 1, configClasses.length);

		configClasses = contextLoader.detectDefaultConfigurationClasses(AnnotatedFooConfigInnerClassTestCase.class);
		assertNotNull(configClasses);
		assertEquals("annotated static FooConfig should be considered.", 1, configClasses.length);
	}
