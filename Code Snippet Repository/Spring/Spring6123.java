	@Test
	public void testReflectionHelperCompareArguments_ExactMatching() {
		StandardTypeConverter tc = new StandardTypeConverter();

		// Calling foo(String) with (String) is exact match
		checkMatch(new Class[] {String.class}, new Class[] {String.class}, tc, ReflectionHelper.ArgumentsMatchKind.EXACT);

		// Calling foo(String,Integer) with (String,Integer) is exact match
		checkMatch(new Class[] {String.class, Integer.class}, new Class[] {String.class, Integer.class}, tc, ArgumentsMatchKind.EXACT);
	}
