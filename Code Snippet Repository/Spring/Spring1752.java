	@Test
	public void usingMapAsValueSource() throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("one", "1");
		map.put("two", "2");
		map.put("three", "3");
		PropertiesEditor pe = new PropertiesEditor();
		pe.setValue(map);
		Object value = pe.getValue();
		assertNotNull(value);
		assertTrue(value instanceof Properties);
		Properties props = (Properties) value;
		assertEquals(3, props.size());
		assertEquals("1", props.getProperty("one"));
		assertEquals("2", props.getProperty("two"));
		assertEquals("3", props.getProperty("three"));
	}
