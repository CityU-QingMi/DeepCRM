	@Test
	void findMethodsWithShadowingUsingHierarchyDownMode() throws Exception {
		assertThat(findMethods(MethodShadowingChild.class, methodContains1, TOP_DOWN))//
				.containsExactly(MethodShadowingChild.class.getMethod("method1", String.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains2, TOP_DOWN))//
				.containsExactly(MethodShadowingInterface.class.getMethod("method2", int.class, int.class),
					MethodShadowingParent.class.getMethod("method2", int.class, int.class, int.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains4, TOP_DOWN))//
				.containsExactly(MethodShadowingChild.class.getMethod("method4", boolean.class));

		assertThat(findMethods(MethodShadowingChild.class, methodContains5, TOP_DOWN))//
				.containsExactly(MethodShadowingParent.class.getMethod("method5", String.class),
					MethodShadowingChild.class.getMethod("method5", Long.class));

		List<Method> methods = findMethods(MethodShadowingChild.class, method -> true, TOP_DOWN);
		assertEquals(6, methods.size());
		assertEquals(MethodShadowingInterface.class.getMethod("method2", int.class, int.class), methods.get(0));
		assertThat(methods.subList(1, 3)).containsOnly(
			MethodShadowingParent.class.getMethod("method2", int.class, int.class, int.class),
			MethodShadowingParent.class.getMethod("method5", String.class));
		assertThat(methods.subList(3, 6)).containsOnly(MethodShadowingChild.class.getMethod("method4", boolean.class),
			MethodShadowingChild.class.getMethod("method1", String.class),
			MethodShadowingChild.class.getMethod("method5", Long.class));
	}
