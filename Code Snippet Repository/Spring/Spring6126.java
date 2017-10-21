	@Test
	public void testConvertArguments() throws Exception {
		StandardTypeConverter tc = new StandardTypeConverter();
		Method oneArg = TestInterface.class.getMethod("oneArg", String.class);
		Method twoArg = TestInterface.class.getMethod("twoArg", String.class, String[].class);

		// basic conversion int>String
		Object[] args = new Object[] {3};
		ReflectionHelper.convertArguments(tc, args, oneArg, null);
		checkArguments(args, "3");

		// varargs but nothing to convert
		args = new Object[] {3};
		ReflectionHelper.convertArguments(tc, args, twoArg, 1);
		checkArguments(args, "3");

		// varargs with nothing needing conversion
		args = new Object[] {3, "abc", "abc"};
		ReflectionHelper.convertArguments(tc, args, twoArg, 1);
		checkArguments(args, "3","abc","abc");

		// varargs with conversion required
		args = new Object[] {3, false ,3.0d};
		ReflectionHelper.convertArguments(tc, args, twoArg, 1);
		checkArguments(args, "3","false","3.0");
	}
