	@Test
	void findAnnotatedMethodsForAnnotationUsedInClassAndSuperClassHierarchyDown() throws Exception {
		Method method1 = ClassWithAnnotatedMethods.class.getDeclaredMethod("method1");
		Method method3 = ClassWithAnnotatedMethods.class.getDeclaredMethod("method3");
		Method superMethod = SuperClassWithAnnotatedMethod.class.getDeclaredMethod("superMethod");

		List<Method> methods = findAnnotatedMethods(ClassWithAnnotatedMethods.class, Annotation1.class, TOP_DOWN);

		assertEquals(3, methods.size());
		assertEquals(superMethod, methods.get(0));
		assertThat(methods.subList(1, 3)).containsOnly(method1, method3);
	}
