	@Test
	public void testConstructorDependencyWithUnresolvableClass() {
		DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
		RootBeanDefinition bd = new RootBeanDefinition(ConstructorDependencyWithClassResolution.class);
		bd.getConstructorArgumentValues().addGenericArgumentValue("java.lang.Strin");
		lbf.registerBeanDefinition("test", bd);
		try {
			lbf.preInstantiateSingletons();
			fail("Should have thrown UnsatisfiedDependencyException");
		}
		catch (UnsatisfiedDependencyException expected) {
			assertTrue(expected.toString().contains("java.lang.Strin"));
		}
	}
