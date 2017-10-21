	@Test
	void findAnnotatedMethodsForAnnotationOnMethodsInClassHierarchyUsingHierarchyUpMode() throws Exception {
		Method method1 = ClassWithAnnotatedMethods.class.getDeclaredMethod("method1");
		Method method3 = ClassWithAnnotatedMethods.class.getDeclaredMethod("method3");
		Method superMethod = SuperClassWithAnnotatedMethod.class.getDeclaredMethod("superMethod");

		List<Method> methods = findAnnotatedMethods(ClassWithAnnotatedMethods.class, Annotation1.class, BOTTOM_UP);

		assertEquals(3, methods.size());
		assertThat(methods.subList(0, 2)).containsOnly(method1, method3);
		assertEquals(superMethod, methods.get(2));
	}
