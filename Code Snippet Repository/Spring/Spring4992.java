	@Test
	public void getNames() {
		Constants c = new Constants(A.class);

		Set<?> names = c.getNames("");
		assertEquals(c.getSize(), names.size());
		assertTrue(names.contains("DOG"));
		assertTrue(names.contains("CAT"));
		assertTrue(names.contains("S1"));

		names = c.getNames("D");
		assertEquals(1, names.size());
		assertTrue(names.contains("DOG"));

		names = c.getNames("d");
		assertEquals(1, names.size());
		assertTrue(names.contains("DOG"));
	}
