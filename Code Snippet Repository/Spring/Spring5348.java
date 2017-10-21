	private void assertMultipleAnnotationsWithIdenticalAttributeNames(AnnotationMetadata metadata) {
		AnnotationAttributes attributes1 = (AnnotationAttributes) metadata.getAnnotationAttributes(
				NamedAnnotation1.class.getName(), false);
		String name1 = attributes1.getString("name");
		assertThat("name of NamedAnnotation1", name1, is("name 1"));

		AnnotationAttributes attributes2 = (AnnotationAttributes) metadata.getAnnotationAttributes(
				NamedAnnotation2.class.getName(), false);
		String name2 = attributes2.getString("name");
		assertThat("name of NamedAnnotation2", name2, is("name 2"));

		AnnotationAttributes attributes3 = (AnnotationAttributes) metadata.getAnnotationAttributes(
				NamedAnnotation3.class.getName(), false);
		String name3 = attributes3.getString("name");
		assertThat("name of NamedAnnotation3", name3, is("name 3"));
	}
