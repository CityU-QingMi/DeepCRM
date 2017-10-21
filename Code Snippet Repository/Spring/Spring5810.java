	@Test
	public void testVarargsInvocation01() {
		// Calling 'Fruit(String... strings)'
		evaluate("new org.springframework.expression.spel.testresources.Fruit('a','b','c').stringscount()", 3,
			Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit('a').stringscount()", 1, Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit().stringscount()", 0, Integer.class);
		// all need converting to strings
		evaluate("new org.springframework.expression.spel.testresources.Fruit(1,2,3).stringscount()", 3, Integer.class);
		// needs string conversion
		evaluate("new org.springframework.expression.spel.testresources.Fruit(1).stringscount()", 1, Integer.class);
		// first and last need conversion
		evaluate("new org.springframework.expression.spel.testresources.Fruit(1,'a',3.0d).stringscount()", 3,
			Integer.class);
	}
