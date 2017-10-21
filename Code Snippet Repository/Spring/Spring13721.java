	@Test
	public void filterSingleKeyModel() throws Exception {
		view.setExtractValueFromSingleKeyModel(true);

		Map<String, Object> model = new HashMap<>();
		TestBeanSimple bean = new TestBeanSimple();
		model.put("foo", bean);

		Object actual = view.filterModel(model);

		assertSame(bean, actual);
	}
