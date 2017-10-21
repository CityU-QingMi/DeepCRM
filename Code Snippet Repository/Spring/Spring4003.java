	@Test
	public void testAddListOfTheSameObjects() throws Exception {
		List<TestBean> beans = new ArrayList<>();
		beans.add(new TestBean("one"));
		beans.add(new TestBean("two"));
		beans.add(new TestBean("three"));
		ModelMap model = new ModelMap();
		model.addAllAttributes(beans);
		assertEquals(1, model.size());
	}
