	@Test
	public void findMergedAnnotationWithLocalAliasesThatConflictWithAttributesInMetaAnnotationByConvention() {
		final String[] EMPTY = new String[0];
		Class<?> element = SpringAppConfigClass.class;
		ContextConfig contextConfig = findMergedAnnotation(element, ContextConfig.class);

		assertNotNull("Should find @ContextConfig on " + element, contextConfig);
		assertArrayEquals("locations for " + element, EMPTY, contextConfig.locations());
		// 'value' in @SpringAppConfig should not override 'value' in @ContextConfig
		assertArrayEquals("value for " + element, EMPTY, contextConfig.value());
		assertArrayEquals("classes for " + element, new Class<?>[] {Number.class}, contextConfig.classes());
	}
