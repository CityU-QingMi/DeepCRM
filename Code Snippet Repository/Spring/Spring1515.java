	@Test
	public void beanDefinitionMerging() {
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.getConstructorArgumentValues().addGenericArgumentValue("test");
		bd.getConstructorArgumentValues().addIndexedArgumentValue(1, new Integer(5));
		bd.getPropertyValues().add("name", "myName");
		bd.getPropertyValues().add("age", "99");
		bd.setQualifiedElement(getClass());

		GenericBeanDefinition childBd = new GenericBeanDefinition();
		childBd.setParentName("bd");

		RootBeanDefinition mergedBd = new RootBeanDefinition(bd);
		mergedBd.overrideFrom(childBd);
		assertEquals(2, mergedBd.getConstructorArgumentValues().getArgumentCount());
		assertEquals(2, mergedBd.getPropertyValues().size());
		assertEquals(bd, mergedBd);

		mergedBd.getConstructorArgumentValues().getArgumentValue(1, null).setValue(new Integer(9));
		assertEquals(new Integer(5), bd.getConstructorArgumentValues().getArgumentValue(1, null).getValue());
		assertEquals(getClass(), bd.getQualifiedElement());
	}
