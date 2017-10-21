	@Test
	public void isEmptyCollection() {
		assertTrue(isEmpty(Collections.emptyList()));
		assertTrue(isEmpty(Collections.emptySet()));

		Set<String> set = new HashSet<>();
		set.add("foo");
		assertFalse(isEmpty(set));
		assertFalse(isEmpty(Arrays.asList("foo")));
	}
