	@Test
	public void stringConcatenationWithDebugLogging() {
		GenericApplicationContext ac = new GenericApplicationContext();
		AnnotationConfigUtils.registerAnnotationConfigProcessors(ac);

		GenericBeanDefinition bd = new GenericBeanDefinition();
		bd.setBeanClass(String.class);
		bd.getConstructorArgumentValues().addGenericArgumentValue("test-#{ T(java.lang.System).currentTimeMillis() }");
		ac.registerBeanDefinition("str", bd);
		ac.refresh();

		String str = ac.getBean("str", String.class);
		assertTrue(str.startsWith("test-"));
	}
