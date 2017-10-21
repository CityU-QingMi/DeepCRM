	@Test
	public void testInheritedAnnotationFromBaseClassDoesMatch() throws Exception {
		MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
		String classUnderTest = "org.springframework.core.type.AnnotationTypeFilterTests$SomeSubClassOfSomeComponent";
		MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classUnderTest);

		AnnotationTypeFilter filter = new AnnotationTypeFilter(InheritedAnnotation.class);
		assertTrue(filter.match(metadataReader, metadataReaderFactory));
		ClassloadingAssertions.assertClassNotLoaded(classUnderTest);
	}
