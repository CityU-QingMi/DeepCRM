	@Test
	public void testMergeArrayIntoCollection() {
		Object[] arr = new Object[] {"value1", "value2"};
		List<Comparable<?>> list = new LinkedList<>();
		list.add("value3");

		CollectionUtils.mergeArrayIntoCollection(arr, list);
		assertEquals("value3", list.get(0));
		assertEquals("value1", list.get(1));
		assertEquals("value2", list.get(2));
	}
