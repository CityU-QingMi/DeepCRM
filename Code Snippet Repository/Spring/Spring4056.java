	@Test
	public void testSpecificEditorForNestedIndexedField() {
		IndexedTestBean tb = new IndexedTestBean();
		tb.getArray()[0].setNestedIndexedBean(new IndexedTestBean());
		tb.getArray()[1].setNestedIndexedBean(new IndexedTestBean());
		DataBinder binder = new DataBinder(tb, "tb");
		binder.registerCustomEditor(String.class, "array[0].nestedIndexedBean.list.name", new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				setValue("list" + text);
			}
			@Override
			public String getAsText() {
				return ((String) getValue()).substring(4);
			}
		});
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("array[0].nestedIndexedBean.list[0].name", "test1");
		pvs.add("array[1].nestedIndexedBean.list[1].name", "test2");
		binder.bind(pvs);
		assertEquals("listtest1", ((TestBean)tb.getArray()[0].getNestedIndexedBean().getList().get(0)).getName());
		assertEquals("test2", ((TestBean)tb.getArray()[1].getNestedIndexedBean().getList().get(1)).getName());
		assertEquals("test1", binder.getBindingResult().getFieldValue("array[0].nestedIndexedBean.list[0].name"));
		assertEquals("test2", binder.getBindingResult().getFieldValue("array[1].nestedIndexedBean.list[1].name"));
	}
