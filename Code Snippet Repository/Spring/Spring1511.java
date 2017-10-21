	@Test
	public void beanDefinitionEqualityWithPropertyValues() {
		RootBeanDefinition bd = new RootBeanDefinition(TestBean.class);
		bd.getPropertyValues().add("name", "myName");
		bd.getPropertyValues().add("age", "99");
		RootBeanDefinition otherBd = new RootBeanDefinition(TestBean.class);
		otherBd.getPropertyValues().add("name", "myName");
		assertTrue(!bd.equals(otherBd));
		assertTrue(!otherBd.equals(bd));
		otherBd.getPropertyValues().add("age", "11");
		assertTrue(!bd.equals(otherBd));
		assertTrue(!otherBd.equals(bd));
		otherBd.getPropertyValues().add("age", "99");
		assertTrue(bd.equals(otherBd));
		assertTrue(otherBd.equals(bd));
		assertTrue(bd.hashCode() == otherBd.hashCode());
	}
