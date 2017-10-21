	@Test
	public void testSunnyDaySetAsTextYieldsSingleValue() throws Exception {
		CustomCollectionEditor editor = new CustomCollectionEditor(ArrayList.class);
		editor.setValue("0, 1, 2");
		Object value = editor.getValue();
		assertNotNull(value);
		assertTrue(value instanceof ArrayList);
		List<?> list = (List<?>) value;
		assertEquals("There must be 1 element in the converted collection", 1, list.size());
		assertEquals("0, 1, 2", list.get(0));
	}
