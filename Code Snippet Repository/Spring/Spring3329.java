	@Test
	public void getUniqueDeclaredMethods_withCovariantReturnType_andCglibRewrittenMethodNames() throws Exception {
		Class<?> cglibLeaf = new ConfigurationClassEnhancer().enhance(Leaf.class, null);
		int m1MethodCount = 0;
		Method[] methods = ReflectionUtils.getUniqueDeclaredMethods(cglibLeaf);
		for (Method method : methods) {
			if (method.getName().equals("m1")) {
				m1MethodCount++;
			}
		}
		assertThat(m1MethodCount, is(1));
		for (Method method : methods) {
			if (method.getName().contains("m1")) {
				assertEquals(method.getReturnType(), Integer.class);
			}
		}
	}
