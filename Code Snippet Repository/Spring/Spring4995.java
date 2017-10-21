	@Test
	public void suffixAccess() {
		Constants c = new Constants(A.class);

		Set<?> names = c.getNamesForSuffix("_PROPERTY");
		assertEquals(2, names.size());
		assertTrue(names.contains("NO_PROPERTY"));
		assertTrue(names.contains("YES_PROPERTY"));

		Set<?> values = c.getValuesForSuffix("_PROPERTY");
		assertEquals(2, values.size());
		assertTrue(values.contains(new Integer(3)));
		assertTrue(values.contains(new Integer(4)));
	}
