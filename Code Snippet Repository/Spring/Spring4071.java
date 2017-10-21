	@Test
	public void testAutoGrowWithinDefaultLimit() throws Exception {
		TestBean testBean = new TestBean();
		DataBinder binder = new DataBinder(testBean, "testBean");

		MutablePropertyValues mpvs = new MutablePropertyValues();
		mpvs.add("friends[4]", "");
		binder.bind(mpvs);

		assertEquals(5, testBean.getFriends().size());
	}
