	@Test
	public void testUninitializedArrayPropertyWithCustomEditor() {
		IndexedTestBean bean = new IndexedTestBean(false);
		BeanWrapper bw = new BeanWrapperImpl(bean);
		PropertyEditor pe = new CustomNumberEditor(Integer.class, true);
		bw.registerCustomEditor(null, "list.age", pe);
		TestBean tb = new TestBean();
		bw.setPropertyValue("list", new ArrayList<>());
		bw.setPropertyValue("list[0]", tb);
		assertEquals(tb, bean.getList().get(0));
		assertEquals(pe, bw.findCustomEditor(int.class, "list.age"));
		assertEquals(pe, bw.findCustomEditor(null, "list.age"));
		assertEquals(pe, bw.findCustomEditor(int.class, "list[0].age"));
		assertEquals(pe, bw.findCustomEditor(null, "list[0].age"));
	}
