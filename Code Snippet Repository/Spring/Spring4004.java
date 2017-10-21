	@Test
	public void testMergeMapWithOverriding() throws Exception {
		Map<String, TestBean> beans = new HashMap<>();
		beans.put("one", new TestBean("one"));
		beans.put("two", new TestBean("two"));
		beans.put("three", new TestBean("three"));
		ModelMap model = new ModelMap();
		model.put("one", new TestBean("oneOld"));
		model.mergeAttributes(beans);
		assertEquals(3, model.size());
		assertEquals("oneOld", ((TestBean) model.get("one")).getName());
	}
