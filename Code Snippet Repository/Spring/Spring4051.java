	@Test
	public void testBindingStringArrayToIntegerSet() {
		IndexedTestBean tb = new IndexedTestBean();
		DataBinder binder = new DataBinder(tb, "tb");
		binder.registerCustomEditor(Set.class, new CustomCollectionEditor(TreeSet.class) {
			@Override
			protected Object convertElement(Object element) {
				return new Integer(element.toString());
			}
		});
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("set", new String[] {"10", "20", "30"});
		binder.bind(pvs);

		assertEquals(tb.getSet(), binder.getBindingResult().getFieldValue("set"));
		assertTrue(tb.getSet() instanceof TreeSet);
		assertEquals(3, tb.getSet().size());
		assertTrue(tb.getSet().contains(new Integer(10)));
		assertTrue(tb.getSet().contains(new Integer(20)));
		assertTrue(tb.getSet().contains(new Integer(30)));

		pvs = new MutablePropertyValues();
		pvs.add("set", null);
		binder.bind(pvs);

		assertNull(tb.getSet());
	}
