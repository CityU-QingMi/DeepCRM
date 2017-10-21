	@Test
	public void testReflectionHelperCompareArguments_RequiresConversionMatching() {
		StandardTypeConverter tc = new StandardTypeConverter();

		// Calling foo(String,int) with (String,Integer) requires boxing conversion of argument one
		checkMatch(new Class[] {String.class, Integer.TYPE}, new Class[] {String.class,Integer.class},tc, ArgumentsMatchKind.CLOSE);

		// Passing (int,String) on call to foo(Integer,String) requires boxing conversion of argument zero
		checkMatch(new Class[] {Integer.TYPE, String.class}, new Class[] {Integer.class, String.class},tc, ArgumentsMatchKind.CLOSE);

		// Passing (int,Sub) on call to foo(Integer,Super) requires boxing conversion of argument zero
		checkMatch(new Class[] {Integer.TYPE, Sub.class}, new Class[] {Integer.class, Super.class}, tc, ArgumentsMatchKind.CLOSE);

		// Passing (int,Sub,boolean) on call to foo(Integer,Super,Boolean) requires boxing conversion of arguments zero and two
		// TODO checkMatch(new Class[] {Integer.TYPE, Sub.class, Boolean.TYPE}, new Class[] {Integer.class, Super.class, Boolean.class}, tc, ArgsMatchKind.REQUIRES_CONVERSION);
	}
