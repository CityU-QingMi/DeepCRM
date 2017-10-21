	@Test
	public void testBindingWithResortedList() {
		IndexedTestBean tb = new IndexedTestBean();
		DataBinder binder = new DataBinder(tb, "tb");
		MutablePropertyValues pvs = new MutablePropertyValues();
		TestBean tb1 = new TestBean("tb1", 99);
		TestBean tb2 = new TestBean("tb2", 99);
		pvs.add("list[0]", tb1);
		pvs.add("list[1]", tb2);
		binder.bind(pvs);
		assertEquals(tb1.getName(), binder.getBindingResult().getFieldValue("list[0].name"));
		assertEquals(tb2.getName(), binder.getBindingResult().getFieldValue("list[1].name"));
		tb.getList().set(0, tb2);
		tb.getList().set(1, tb1);
		assertEquals(tb2.getName(), binder.getBindingResult().getFieldValue("list[0].name"));
		assertEquals(tb1.getName(), binder.getBindingResult().getFieldValue("list[1].name"));
	}
