	private void doTestSubClassAnnotationInfo(AnnotationMetadata metadata) {
		assertThat(metadata.getClassName(), is(AnnotatedComponentSubClass.class.getName()));
		assertThat(metadata.isInterface(), is(false));
		assertThat(metadata.isAnnotation(), is(false));
		assertThat(metadata.isAbstract(), is(false));
		assertThat(metadata.isConcrete(), is(true));
		assertThat(metadata.hasSuperClass(), is(true));
		assertThat(metadata.getSuperClassName(), is(AnnotatedComponent.class.getName()));
		assertThat(metadata.getInterfaceNames().length, is(0));
		assertThat(metadata.isAnnotated(Component.class.getName()), is(false));
		assertThat(metadata.isAnnotated(Scope.class.getName()), is(false));
		assertThat(metadata.isAnnotated(SpecialAttr.class.getName()), is(false));
		assertThat(metadata.hasAnnotation(Component.class.getName()), is(false));
		assertThat(metadata.hasAnnotation(Scope.class.getName()), is(false));
		assertThat(metadata.hasAnnotation(SpecialAttr.class.getName()), is(false));
		assertThat(metadata.getAnnotationTypes().size(), is(0));
		assertThat(metadata.getAnnotationAttributes(Component.class.getName()), nullValue());
		assertThat(metadata.getAnnotatedMethods(DirectAnnotation.class.getName()).size(), equalTo(0));
		assertThat(metadata.isAnnotated(IsAnnotatedAnnotation.class.getName()), equalTo(false));
		assertThat(metadata.getAllAnnotationAttributes(DirectAnnotation.class.getName()), nullValue());
	}
