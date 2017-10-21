	@Test
	public void testNestedGrowingList() {
		Form form = new Form();
		DataBinder binder = new DataBinder(form, "form");
		MutablePropertyValues mpv = new MutablePropertyValues();
		mpv.add("f[list][0]", "firstValue");
		mpv.add("f[list][1]", "secondValue");
		binder.bind(mpv);
		assertFalse(binder.getBindingResult().hasErrors());
		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) form.getF().get("list");
		assertEquals("firstValue", list.get(0));
		assertEquals("secondValue", list.get(1));
		assertEquals(2, list.size());
	}
