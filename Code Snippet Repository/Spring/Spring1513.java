	@Test
	public void beanDefinitionEqualityWithTypedConstructorArguments() {
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.getConstructorArgumentValues().addGenericArgumentValue("test", "int");
		bd.getConstructorArgumentValues().addIndexedArgumentValue(1, new Integer(5), "long");
		RootBeanDefinition otherBd = new RootBeanDefinition(TestBean.class);
		otherBd.getConstructorArgumentValues().addGenericArgumentValue("test", "int");
		otherBd.getConstructorArgumentValues().addIndexedArgumentValue(1, new Integer(5));
		assertTrue(!bd.equals(otherBd));
		assertTrue(!otherBd.equals(bd));
		otherBd.getConstructorArgumentValues().addIndexedArgumentValue(1, new Integer(5), "int");
		assertTrue(!bd.equals(otherBd));
		assertTrue(!otherBd.equals(bd));
		otherBd.getConstructorArgumentValues().addIndexedArgumentValue(1, new Integer(5), "long");
		assertTrue(bd.equals(otherBd));
		assertTrue(otherBd.equals(bd));
		assertTrue(bd.hashCode() == otherBd.hashCode());
	}
