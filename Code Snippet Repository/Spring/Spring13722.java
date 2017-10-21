	@SuppressWarnings("")
	@Test
	public void filterTwoKeyModel() throws Exception {
		view.setExtractValueFromSingleKeyModel(true);

		Map<String, Object> model = new HashMap<>();
		TestBeanSimple bean1 = new TestBeanSimple();
		TestBeanSimple bean2 = new TestBeanSimple();
		model.put("foo1", bean1);
		model.put("foo2", bean2);

		Object actual = view.filterModel(model);

		assertTrue(actual instanceof Map);
		assertSame(bean1, ((Map) actual).get("foo1"));
		assertSame(bean2, ((Map) actual).get("foo2"));
	}
