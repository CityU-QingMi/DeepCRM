	@Test
	public void testReflectionHelperCompareArguments_CloseMatching() {
		StandardTypeConverter tc = new StandardTypeConverter();

		// Calling foo(List) with (ArrayList) is close match (no conversion required)
		checkMatch(new Class[] {ArrayList.class}, new Class[] {List.class}, tc, ArgumentsMatchKind.CLOSE);

		// Passing (Sub,String) on call to foo(Super,String) is close match
		checkMatch(new Class[] {Sub.class, String.class}, new Class[] {Super.class, String.class}, tc, ArgumentsMatchKind.CLOSE);

		// Passing (String,Sub) on call to foo(String,Super) is close match
		checkMatch(new Class[] {String.class, Sub.class}, new Class[] {String.class, Super.class}, tc, ArgumentsMatchKind.CLOSE);
	}
