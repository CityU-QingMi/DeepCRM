	@Test
	public void testSetAutoGrowCollectionLimit() {
		BeanWithIntegerList tb = new BeanWithIntegerList();
		DataBinder binder = new DataBinder(tb);
		binder.setAutoGrowCollectionLimit(257);
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("integerList[256]", "1");

		binder.bind(pvs);
		assertEquals(257, tb.getIntegerList().size());
		assertEquals(Integer.valueOf(1), tb.getIntegerList().get(256));
		assertEquals(Integer.valueOf(1), binder.getBindingResult().getFieldValue("integerList[256]"));
	}
