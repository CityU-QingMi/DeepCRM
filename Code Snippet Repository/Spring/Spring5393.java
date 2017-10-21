	@Test
	public void testMergePrimitiveArrayIntoCollection() {
		int[] arr = new int[] {1, 2};
		List<Comparable<?>> list = new LinkedList<>();
		list.add(new Integer(3));

		CollectionUtils.mergeArrayIntoCollection(arr, list);
		assertEquals(new Integer(3), list.get(0));
		assertEquals(new Integer(1), list.get(1));
		assertEquals(new Integer(2), list.get(2));
	}
