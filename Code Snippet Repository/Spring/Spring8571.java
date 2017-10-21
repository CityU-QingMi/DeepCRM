	@Test
	@SuppressWarnings("")
	public void findAnnotationDescriptorForTypesWithInheritedAnnotationOnClass() throws Exception {
		// Note: @Transactional is inherited
		assertEquals(
			InheritedAnnotationClass.class,
			findAnnotationDescriptorForTypes(InheritedAnnotationClass.class, Transactional.class).getRootDeclaringClass());
		assertEquals(
			InheritedAnnotationClass.class,
			findAnnotationDescriptorForTypes(SubInheritedAnnotationClass.class, Transactional.class).getRootDeclaringClass());
	}
