	@Test
	void findMethodsWithStaticHidingUsingHierarchyUpMode() throws Exception {
		Class<?> ifc = StaticMethodHidingInterface.class;
		Class<?> parent = StaticMethodHidingParent.class;
		Class<?> child = StaticMethodHidingChild.class;

		Method ifcMethod2 = ifc.getDeclaredMethod("method2", int.class, int.class);
		Method childMethod1 = child.getDeclaredMethod("method1", String.class);
		Method childMethod4 = child.getDeclaredMethod("method4", boolean.class);
		Method childMethod5 = child.getDeclaredMethod("method5", Long.class);
		Method parentMethod2 = parent.getDeclaredMethod("method2", int.class, int.class, int.class);
		Method parentMethod5 = parent.getDeclaredMethod("method5", String.class);

		assertThat(findMethods(child, methodContains1, BOTTOM_UP)).containsExactly(childMethod1);
		assertThat(findMethods(child, methodContains2, BOTTOM_UP)).containsExactly(parentMethod2, ifcMethod2);
		assertThat(findMethods(child, methodContains4, BOTTOM_UP)).containsExactly(childMethod4);
		assertThat(findMethods(child, methodContains5, BOTTOM_UP)).containsExactly(childMethod5, parentMethod5);

		List<Method> methods = findMethods(child, method -> true, BOTTOM_UP);
		assertEquals(6, methods.size());
		assertThat(methods.subList(0, 3)).containsOnly(childMethod1, childMethod4, childMethod5);
		assertThat(methods.subList(3, 5)).containsOnly(parentMethod2, parentMethod5);
		assertEquals(ifcMethod2, methods.get(5));
	}
