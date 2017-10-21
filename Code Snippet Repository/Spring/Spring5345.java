	private void doTestMetadataForInterfaceClass(AnnotationMetadata metadata) {
		assertThat(metadata.getClassName(), is(AnnotationMetadata.class.getName()));
		assertThat(metadata.isInterface(), is(true));
		assertThat(metadata.isAnnotation(), is(false));
		assertThat(metadata.isAbstract(), is(true));
		assertThat(metadata.isConcrete(), is(false));
		assertThat(metadata.hasSuperClass(), is(false));
		assertThat(metadata.getSuperClassName(), nullValue());
		assertThat(metadata.getInterfaceNames().length, is(2));
		assertThat(metadata.getInterfaceNames()[0], is(ClassMetadata.class.getName()));
		assertThat(metadata.getInterfaceNames()[1], is(AnnotatedTypeMetadata.class.getName()));
		assertThat(metadata.getAnnotationTypes().size(), is(0));
	}
