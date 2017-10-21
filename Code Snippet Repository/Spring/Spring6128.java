	@Test
	public void testSetupArguments() {
		Object[] newArray = ReflectionHelper.setupArgumentsForVarargsInvocation(new Class[] {new String[0].getClass()},"a","b","c");

		assertEquals(1, newArray.length);
		Object firstParam = newArray[0];
		assertEquals(String.class,firstParam.getClass().getComponentType());
		Object[] firstParamArray = (Object[])firstParam;
		assertEquals(3,firstParamArray.length);
		assertEquals("a",firstParamArray[0]);
		assertEquals("b",firstParamArray[1]);
		assertEquals("c",firstParamArray[2]);
	}
