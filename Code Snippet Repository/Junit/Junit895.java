	@Test
	void findMethodsWithStaticHidingUsingHierarchyDownMode() throws Exception {
		Class<?> ifc = StaticMethodHidingInterface.class;
		Class<?> parent = StaticMethodHidingParent.class;
		Class<?> child = StaticMethodHidingChild.class;

		Method ifcMethod2 = ifc.getDeclaredMethod("method2", int.class, int.class);
		Method childMethod1 = child.getDeclaredMethod("method1", String.class);
		Method childMethod4 = child.getDeclaredMethod("method4", boolean.class);
		Method childMethod5 = child.getDeclaredMethod("method5", Long.class);
		Method parentMethod2 = parent.getDeclaredMethod("method2", int.class, int.class, int.class);
		Method parentMethod5 = parent.getDeclaredMethod("method5", String.class);

		assertThat(findMethods(child, methodContains1, TOP_DOWN)).containsExactly(childMethod1);
		assertThat(findMethods(child, methodContains2, TOP_DOWN)).containsExactly(ifcMethod2, parentMethod2);
		assertThat(findMethods(child, methodContains4, TOP_DOWN)).containsExactly(childMethod4);
		assertThat(findMethods(child, methodContains5, TOP_DOWN)).containsExactly(parentMethod5, childMethod5);

		List<Method> methods = findMethods(child, method -> true, TOP_DOWN);
		assertEquals(6, methods.size());
		assertEquals(ifcMethod2, methods.get(0));
		assertThat(methods.subList(1, 3)).containsOnly(parentMethod2, parentMethod5);
		assertThat(methods.subList(3, 6)).containsOnly(childMethod1, childMethod4, childMethod5);
	}
