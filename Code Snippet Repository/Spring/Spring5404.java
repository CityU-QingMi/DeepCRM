	@Test
	public void testDuplicateIterators() {
		List<String> list = Arrays.asList("0", "1");
		Iterator<String> iterator = list.iterator();
		CompositeIterator<String> it = new CompositeIterator<>();
		it.add(iterator);
		it.add(list.iterator());
		try {
			it.add(iterator);
			fail();
		}
		catch (IllegalArgumentException ex) {
			// expected
		}
	}
