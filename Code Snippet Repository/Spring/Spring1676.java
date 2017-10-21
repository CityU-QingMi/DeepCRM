	@Test
	public void testRefSubelementsBuildCollection() throws Exception {
		TestBean jen = (TestBean) this.beanFactory.getBean("jenny");
		TestBean dave = (TestBean) this.beanFactory.getBean("david");
		TestBean rod = (TestBean) this.beanFactory.getBean("rod");

		// Must be a list to support ordering
		// Our bean doesn't modify the collection:
		// of course it could be a different copy in a real object.
		Object[] friends = rod.getFriends().toArray();
		assertTrue(friends.length == 2);

		assertTrue("First friend must be jen, not " + friends[0], friends[0] == jen);
		assertTrue(friends[1] == dave);
		// Should be ordered
	}
