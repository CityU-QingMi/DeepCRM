	@Test
	public void testInUse() {
		List<String> list = Arrays.asList("0", "1");
		CompositeIterator<String> it = new CompositeIterator<>();
		it.add(list.iterator());
		it.hasNext();
		try {
			it.add(list.iterator());
			fail();
		}
		catch (IllegalStateException ex) {
			// expected
		}
		it = new CompositeIterator<>();
		it.add(list.iterator());
		it.next();
		try {
			it.add(list.iterator());
			fail();
		}
		catch (IllegalStateException ex) {
			// expected
		}
	}
