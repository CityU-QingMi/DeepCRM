	@Test
	public void testIndexedPropertiesWithListPropertyEditor() {
		IndexedTestBean bean = new IndexedTestBean();
		BeanWrapper bw = new BeanWrapperImpl(bean);
		bw.registerCustomEditor(List.class, "list", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				List<TestBean> result = new ArrayList<>();
				result.add(new TestBean("list" + text, 99));
				setValue(result);
			}
		});
		bw.setPropertyValue("list", "1");
		assertEquals("list1", ((TestBean) bean.getList().get(0)).getName());
		bw.setPropertyValue("list[0]", "test");
		assertEquals("test", bean.getList().get(0));
	}
