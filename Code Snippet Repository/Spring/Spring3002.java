	@Test
	public void methodInjectedBeanMustBeOfSameEnhancedCglibSubclassTypeAcrossBeanFactories() {
		Class<?> firstClass = null;

		for (int i = 0; i < 10; i++) {
			DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
			new XmlBeanDefinitionReader(bf).loadBeanDefinitions(OVERRIDES_CONTEXT);

			final Class<?> currentClass = bf.getBean("overrideOneMethod").getClass();
			assertTrue("Method injected bean class [" + currentClass + "] must be a CGLIB enhanced subclass.",
				ClassUtils.isCglibProxyClass(currentClass));

			if (firstClass == null) {
				firstClass = currentClass;
			}
			else {
				assertEquals(firstClass, currentClass);
			}
		}
	}
