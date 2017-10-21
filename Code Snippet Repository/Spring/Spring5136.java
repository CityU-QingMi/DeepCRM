	@Test
	public void plainComparator() {
		List<Object> items = new ArrayList<>();
		C c = new C(5);
		C c2 = new C(-5);
		items.add(c);
		items.add(c2);
		Collections.sort(items, comparator);
		assertOrder(items, c2, c);
	}
