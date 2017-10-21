	@Test
	void findMethodsUsingHierarchyUpMode() throws Exception {
		assertThat(findMethods(ChildClass.class, method -> method.getName().contains("method"), BOTTOM_UP))//
				.containsExactly(ChildClass.class.getMethod("method4"), ParentClass.class.getMethod("method3"),
					GrandparentInterface.class.getMethod("method2"), GrandparentClass.class.getMethod("method1"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().contains("other"), BOTTOM_UP))//
				.containsExactly(ChildClass.class.getMethod("otherMethod3"),
					ParentClass.class.getMethod("otherMethod2"), GrandparentClass.class.getMethod("otherMethod1"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().equals("method2"), BOTTOM_UP))//
				.containsExactly(ParentClass.class.getMethod("method2"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().equals("wrongName"), BOTTOM_UP)).isEmpty();

		assertThat(findMethods(ParentClass.class, method -> method.getName().contains("method"), BOTTOM_UP))//
				.containsExactly(ParentClass.class.getMethod("method3"),
					GrandparentInterface.class.getMethod("method2"), GrandparentClass.class.getMethod("method1"));
	}
