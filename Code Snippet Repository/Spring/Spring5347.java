	private void assertMetaAnnotationOverrides(AnnotationMetadata metadata) {
		AnnotationAttributes attributes = (AnnotationAttributes) metadata.getAnnotationAttributes(
				TestComponentScan.class.getName(), false);
		String[] basePackages = attributes.getStringArray("basePackages");
		assertThat("length of basePackages[]", basePackages.length, is(1));
		assertThat("basePackages[0]", basePackages[0], is("org.example.componentscan"));
		String[] value = attributes.getStringArray("value");
		assertThat("length of value[]", value.length, is(0));
		Class<?>[] basePackageClasses = attributes.getClassArray("basePackageClasses");
		assertThat("length of basePackageClasses[]", basePackageClasses.length, is(0));
	}
