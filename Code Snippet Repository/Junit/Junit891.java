	@Test
	void findMethodsWithShadowingUsingHierarchyUpMode() throws Exception {
		assertThat(findMethods(MethodShadowingChild.class, methodContains1, BOTTOM_UP))//
				.containsExactly(MethodShadowingChild.class.getMethod("method1", String.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains2, BOTTOM_UP))//
				.containsExactly(MethodShadowingParent.class.getMethod("method2", int.class, int.class, int.class),
					MethodShadowingInterface.class.getMethod("method2", int.class, int.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains4, BOTTOM_UP))//
				.containsExactly(MethodShadowingChild.class.getMethod("method4", boolean.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains5, BOTTOM_UP))//
				.containsExactly(MethodShadowingChild.class.getMethod("method5", Long.class),
					MethodShadowingParent.class.getMethod("method5", String.class));

		List<Method> methods = findMethods(MethodShadowingChild.class, method -> true, BOTTOM_UP);
		assertEquals(6, methods.size());
		assertThat(methods.subList(0, 3)).containsOnly(MethodShadowingChild.class.getMethod("method4", boolean.class),
			MethodShadowingChild.class.getMethod("method1", String.class),
			MethodShadowingChild.class.getMethod("method5", Long.class));
		assertThat(methods.subList(3, 5)).containsOnly(
			MethodShadowingParent.class.getMethod("method2", int.class, int.class, int.class),
			MethodShadowingParent.class.getMethod("method5", String.class));
		assertEquals(MethodShadowingInterface.class.getMethod("method2", int.class, int.class), methods.get(5));
	}
