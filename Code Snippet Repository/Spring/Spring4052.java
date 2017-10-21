	@Test
	public void testBindingNullToEmptyCollection() {
		IndexedTestBean tb = new IndexedTestBean();
		DataBinder binder = new DataBinder(tb, "tb");
		binder.registerCustomEditor(Set.class, new CustomCollectionEditor(TreeSet.class, true));
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("set", null);
		binder.bind(pvs);

		assertTrue(tb.getSet() instanceof TreeSet);
		assertTrue(tb.getSet().isEmpty());
	}
