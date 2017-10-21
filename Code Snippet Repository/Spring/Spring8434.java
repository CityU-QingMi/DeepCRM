	@Test
	@Override
	public void loadContextHierarchy() {
		assertNotNull("child ApplicationContext", context);
		assertNotNull("parent ApplicationContext", context.getParent());
		assertNull("grandparent ApplicationContext", context.getParent().getParent());
		assertEquals("parent", parent);
		assertEquals("parent + test user", user);
		assertEquals("from TestUserConfig", beanFromTestUserConfig);
		assertNull("Bean from UserConfig should not be present.", beanFromUserConfig);
	}
