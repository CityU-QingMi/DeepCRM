	@Test
	public void getUniqueDeclaredMethods_withCovariantReturnType() throws Exception {
		class Parent {
			@SuppressWarnings("unused")
			public Number m1() {
				return new Integer(42);
			}
		}
		class Leaf extends Parent {
			@Override
			public Integer m1() {
				return new Integer(42);
			}
		}
		int m1MethodCount = 0;
		Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(Leaf.class);
		for (Method method : methods) {
			if (method.getName().equals("m1")) {
				m1MethodCount++;
			}
		}
		assertThat(m1MethodCount, is(1));
		assertTrue(ObjectUtils.containsElement(methods, Leaf.class.getMethod("m1")));
		assertFalse(ObjectUtils.containsElement(methods, Parent.class.getMethod("m1")));
	}
