	@Test
	public void testAndOrNotReplacement() {
		TypePatternClassFilter tpcf = new TypePatternClassFilter("java.lang.Object or java.lang.String");
		assertFalse("matches Number",tpcf.matches(Number.class));
		assertTrue("matches Object",tpcf.matches(Object.class));
		assertTrue("matchesString",tpcf.matches(String.class));
		tpcf = new TypePatternClassFilter("java.lang.Number+ and java.lang.Float");
		assertTrue("matches Float",tpcf.matches(Float.class));
		assertFalse("matches Double",tpcf.matches(Double.class));
		tpcf = new TypePatternClassFilter("java.lang.Number+ and not java.lang.Float");
		assertFalse("matches Float",tpcf.matches(Float.class));
		assertTrue("matches Double",tpcf.matches(Double.class));
	}
