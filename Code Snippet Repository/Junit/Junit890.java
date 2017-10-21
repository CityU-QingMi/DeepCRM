	@Test
	void findMethodsUsingHierarchyDownMode() throws Exception {
		assertThat(findMethods(ChildClass.class, method -> method.getName().contains("method"), TOP_DOWN))//
				.containsExactly(GrandparentClass.class.getMethod("method1"),
					GrandparentInterface.class.getMethod("method2"), ParentClass.class.getMethod("method3"),
					ChildClass.class.getMethod("method4"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().contains("other"), TOP_DOWN))//
				.containsExactly(GrandparentClass.class.getMethod("otherMethod1"),
					ParentClass.class.getMethod("otherMethod2"), ChildClass.class.getMethod("otherMethod3"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().equals("method2"), TOP_DOWN))//
				.containsExactly(ParentClass.class.getMethod("method2"));

		assertThat(findMethods(ChildClass.class, method -> method.getName().equals("wrongName"), TOP_DOWN)).isEmpty();

		assertThat(findMethods(ParentClass.class, method -> method.getName().contains("method"), TOP_DOWN))//
				.containsExactly(GrandparentClass.class.getMethod("method1"),
					GrandparentInterface.class.getMethod("method2"), ParentClass.class.getMethod("method3"));
	}
