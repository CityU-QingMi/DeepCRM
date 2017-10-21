	@Test
	@Override
	public void loadContextHierarchy() {
		assertNotNull("child ApplicationContext", context);
		assertNotNull("parent ApplicationContext", context.getParent());
		assertNull("grandparent ApplicationContext", context.getParent().getParent());
		assertEquals("foo-level-2", foo);
		assertEquals("bar", bar);
		assertEquals("baz", baz);
	}
