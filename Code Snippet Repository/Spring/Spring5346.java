	private void doTestMetadataForAnnotationClass(AnnotationMetadata metadata) {
		assertThat(metadata.getClassName(), is(Component.class.getName()));
		assertThat(metadata.isInterface(), is(true));
		assertThat(metadata.isAnnotation(), is(true));
		assertThat(metadata.isAbstract(), is(true));
		assertThat(metadata.isConcrete(), is(false));
		assertThat(metadata.hasSuperClass(), is(false));
		assertThat(metadata.getSuperClassName(), nullValue());
		assertThat(metadata.getInterfaceNames().length, is(1));
		assertThat(metadata.getInterfaceNames()[0], is(Annotation.class.getName()));
		assertThat(metadata.isAnnotated(Documented.class.getName()), is(false));
		assertThat(metadata.isAnnotated(Scope.class.getName()), is(false));
		assertThat(metadata.isAnnotated(SpecialAttr.class.getName()), is(false));
		assertThat(metadata.hasAnnotation(Documented.class.getName()), is(true));
		assertThat(metadata.hasAnnotation(Scope.class.getName()), is(false));
		assertThat(metadata.hasAnnotation(SpecialAttr.class.getName()), is(false));
		assertThat(metadata.getAnnotationTypes().size(), is(4));
	}
