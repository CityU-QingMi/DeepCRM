	@Test
	public void testInheritedAnnotationFromInterfaceDoesNotMatch() throws Exception {
		MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
		String classUnderTest = "org.springframework.core.type.AnnotationTypeFilterTests$SomeSubClassOfSomeComponentInterface";
		MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classUnderTest);

		AnnotationTypeFilter filter = new AnnotationTypeFilter(InheritedAnnotation.class);
		// Must fail as annotation on interfaces should not be considered a match
		assertFalse(filter.match(metadataReader, metadataReaderFactory));
		ClassloadingAssertions.assertClassNotLoaded(classUnderTest);
	}
