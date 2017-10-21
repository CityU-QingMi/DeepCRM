	@Test
	public void testBindingNoErrors() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod, "person");
		assertTrue(binder.isIgnoreUnknownFields());
		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("name", "Rod");
		pvs.add("age", "032");
		pvs.add("nonExisting", "someValue");

		binder.bind(pvs);
		binder.close();

		assertTrue("changed name correctly", rod.getName().equals("Rod"));
		assertTrue("changed age correctly", rod.getAge() == 32);

		Map<?, ?> map = binder.getBindingResult().getModel();
		assertTrue("There is one element in map", map.size() == 2);
		TestBean tb = (TestBean) map.get("person");
		assertTrue("Same object", tb.equals(rod));

		BindingResult other = new BeanPropertyBindingResult(rod, "person");
		assertEquals(other, binder.getBindingResult());
		assertEquals(binder.getBindingResult(), other);
		BindException ex = new BindException(other);
		assertEquals(ex, other);
		assertEquals(other, ex);
		assertEquals(ex, binder.getBindingResult());
		assertEquals(binder.getBindingResult(), ex);

		other.reject("xxx");
		assertTrue(!other.equals(binder.getBindingResult()));
	}
