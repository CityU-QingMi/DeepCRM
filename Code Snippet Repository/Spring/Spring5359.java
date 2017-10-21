	@Test
	public void directMatch() throws Exception {
		MetadataReaderFactory metadataReaderFactory = new SimpleMetadataReaderFactory();
		String classUnderTest = "org.springframework.core.type.AssignableTypeFilterTests$TestNonInheritingClass";
		MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(classUnderTest);

		AssignableTypeFilter matchingFilter = new AssignableTypeFilter(TestNonInheritingClass.class);
		AssignableTypeFilter notMatchingFilter = new AssignableTypeFilter(TestInterface.class);
		assertFalse(notMatchingFilter.match(metadataReader, metadataReaderFactory));
		assertTrue(matchingFilter.match(metadataReader, metadataReaderFactory));
	}
