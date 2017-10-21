	@Test
	public void testBindingWithAllowedAndDisallowedMapFields() throws Exception {
		TestBean rod = new TestBean();
		DataBinder binder = new DataBinder(rod);
		binder.setAllowedFields("someMap[key1]", "someMap[key2]");
		binder.setDisallowedFields("someMap['key3']", "someMap[key4]");

		MutablePropertyValues pvs = new MutablePropertyValues();
		pvs.add("someMap[key1]", "value1");
		pvs.add("someMap['key2']", "value2");
		pvs.add("someMap[key3]", "value3");
		pvs.add("someMap['key4']", "value4");

		binder.bind(pvs);
		binder.close();
		assertEquals("value1", rod.getSomeMap().get("key1"));
		assertEquals("value2", rod.getSomeMap().get("key2"));
		assertNull(rod.getSomeMap().get("key3"));
		assertNull(rod.getSomeMap().get("key4"));
		String[] disallowedFields = binder.getBindingResult().getSuppressedFields();
		assertEquals(2, disallowedFields.length);
		assertTrue(ObjectUtils.containsElement(disallowedFields, "someMap[key3]"));
		assertTrue(ObjectUtils.containsElement(disallowedFields, "someMap[key4]"));
	}
