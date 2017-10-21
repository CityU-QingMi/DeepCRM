	@Test
	public void setGenericArrayProperty() {
		SkipReaderStub target = new SkipReaderStub();
		AbstractPropertyAccessor accessor = createAccessor(target);
		List<String> values = new LinkedList<>();
		values.add("1");
		values.add("2");
		values.add("3");
		values.add("4");
		accessor.setPropertyValue("items", values);
		Object[] result = target.items;
		assertEquals(4, result.length);
		assertEquals("1", result[0]);
		assertEquals("2", result[1]);
		assertEquals("3", result[2]);
		assertEquals("4", result[3]);
	}
