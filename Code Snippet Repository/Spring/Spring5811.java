	@Test
	public void testVarargsInvocation02() {
		// Calling 'Fruit(int i, String... strings)' - returns int+length_of_strings
		evaluate("new org.springframework.expression.spel.testresources.Fruit(5,'a','b','c').stringscount()", 8,
			Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit(2,'a').stringscount()", 3, Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit(4).stringscount()", 4, Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit(8,2,3).stringscount()", 10, Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit(9).stringscount()", 9, Integer.class);
		evaluate("new org.springframework.expression.spel.testresources.Fruit(2,'a',3.0d).stringscount()", 4,
			Integer.class);
		evaluate(
			"new org.springframework.expression.spel.testresources.Fruit(8,stringArrayOfThreeItems).stringscount()",
			11, Integer.class);
	}
