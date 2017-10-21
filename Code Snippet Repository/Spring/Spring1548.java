	@Test
	public void testGenericMatchingWithFullTypeDifferentiation() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.setAutowireCandidateResolver(new GenericTypeAwareAutowireCandidateResolver());

		bf.registerBeanDefinition("store1", new RootBeanDefinition(DoubleStore.class));
		bf.registerBeanDefinition("store2", new RootBeanDefinition(FloatStore.class));
		bf.registerBeanDefinition("numberBean",
				new RootBeanDefinition(NumberBean.class, RootBeanDefinition.AUTOWIRE_CONSTRUCTOR, false));

		NumberBean nb = bf.getBean(NumberBean.class);
		assertSame(bf.getBean("store1"), nb.getDoubleStore());
		assertSame(bf.getBean("store2"), nb.getFloatStore());

		String[] numberStoreNames = bf.getBeanNamesForType(ResolvableType.forClass(NumberStore.class));
		String[] doubleStoreNames = bf.getBeanNamesForType(ResolvableType.forClassWithGenerics(NumberStore.class, Double.class));
		String[] floatStoreNames = bf.getBeanNamesForType(ResolvableType.forClassWithGenerics(NumberStore.class, Float.class));
		assertEquals(2, numberStoreNames.length);
		assertEquals("store1", numberStoreNames[0]);
		assertEquals("store2", numberStoreNames[1]);
		assertEquals(1, doubleStoreNames.length);
		assertEquals("store1", doubleStoreNames[0]);
		assertEquals(1, floatStoreNames.length);
		assertEquals("store2", floatStoreNames[0]);
	}
