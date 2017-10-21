	@Test
	public void sortWithNulls() {
		List<Object> list = new ArrayList<>();
		list.add(null);
		list.add(B.class);
		list.add(null);
		list.add(A.class);
		AnnotationAwareOrderComparator.sort(list);
		assertEquals(A.class, list.get(0));
		assertEquals(B.class, list.get(1));
		assertNull(list.get(2));
		assertNull(list.get(3));
	}
