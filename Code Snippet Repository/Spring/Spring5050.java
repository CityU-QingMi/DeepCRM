	@Test
	public void findMergedAnnotationForMultipleMetaAnnotationsWithClashingAttributeNames() {
		String[] xmlLocations = asArray("test.xml");
		String[] propFiles = asArray("test.properties");

		Class<?> element = AliasedComposedContextConfigAndTestPropSourceClass.class;

		ContextConfig contextConfig = findMergedAnnotation(element, ContextConfig.class);
		assertNotNull("@ContextConfig on " + element, contextConfig);
		assertArrayEquals("locations", xmlLocations, contextConfig.locations());
		assertArrayEquals("value", xmlLocations, contextConfig.value());

		// Synthesized annotation
		TestPropSource testPropSource = AnnotationUtils.findAnnotation(element, TestPropSource.class);
		assertArrayEquals("locations", propFiles, testPropSource.locations());
		assertArrayEquals("value", propFiles, testPropSource.value());

		// Merged annotation
		testPropSource = findMergedAnnotation(element, TestPropSource.class);
		assertNotNull("@TestPropSource on " + element, testPropSource);
		assertArrayEquals("locations", propFiles, testPropSource.locations());
		assertArrayEquals("value", propFiles, testPropSource.value());
	}
