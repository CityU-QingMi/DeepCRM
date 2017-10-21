	@Test
	public void testAutoGrowWithinCustomLimit() throws Exception {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");
		binder.setAutoGrowCollectionLimit(10);

		MutablePropertyValues mpvs = new MutablePropertyValues();
		mpvs.add("friends[4]", "");
		binder.bind(mpvs);

		assertEquals(5, testBean.getFriends().size());
	}
