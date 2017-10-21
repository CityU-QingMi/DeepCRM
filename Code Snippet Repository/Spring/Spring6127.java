	@Test
	public void testConvertArguments2() throws Exception {
		StandardTypeConverter tc = new StandardTypeConverter();
		Method oneArg = TestInterface.class.getMethod("oneArg", String.class);
		Method twoArg = TestInterface.class.getMethod("twoArg", String.class, String[].class);

		// Simple conversion: int to string
		Object[] args = new Object[] {3};
		ReflectionHelper.convertAllArguments(tc, args, oneArg);
		checkArguments(args,"3");

		// varargs conversion
		args = new Object[] {3, false, 3.0f};
		ReflectionHelper.convertAllArguments(tc, args, twoArg);
		checkArguments(args,"3","false","3.0");

		// varargs conversion but no varargs
		args = new Object[] {3};
		ReflectionHelper.convertAllArguments(tc, args, twoArg);
		checkArguments(args,"3");

		// null value
		args = new Object[] {3, null, 3.0f};
		ReflectionHelper.convertAllArguments(tc, args, twoArg);
		checkArguments(args,"3",null,"3.0");
	}
