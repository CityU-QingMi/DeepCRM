	@Test
	public void testRefSubelementsBuildCollectionWithPrototypes() throws Exception {
		TestBean jen = (TestBean) this.beanFactory.getBean("pJenny");
		TestBean dave = (TestBean) this.beanFactory.getBean("pDavid");
		TestBean rod = (TestBean) this.beanFactory.getBean("pRod");

		Object[] friends = rod.getFriends().toArray();
		assertTrue(friends.length == 2);
		assertTrue("First friend must be jen, not " + friends[0],
				friends[0].toString().equals(jen.toString()));
		assertTrue("Jen not same instance", friends[0] != jen);
		assertTrue(friends[1].toString().equals(dave.toString()));
		assertTrue("Dave not same instance", friends[1] != dave);
		assertEquals("Jen", dave.getSpouse().getName());

		TestBean rod2 = (TestBean) this.beanFactory.getBean("pRod");
		Object[] friends2 = rod2.getFriends().toArray();
		assertTrue(friends2.length == 2);
		assertTrue("First friend must be jen, not " + friends2[0],
				friends2[0].toString().equals(jen.toString()));
		assertTrue("Jen not same instance", friends2[0] != friends[0]);
		assertTrue(friends2[1].toString().equals(dave.toString()));
		assertTrue("Dave not same instance", friends2[1] != friends[1]);
	}
