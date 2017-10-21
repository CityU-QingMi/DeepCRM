	@SuppressWarnings("")
	@Test
	public void operatorInstanceOf() throws Exception {
		expression = parse("'xyz' instanceof T(String)");
		assertEquals(true, expression.getValue());
		assertCanCompile(expression);
		assertEquals(true, expression.getValue());

		expression = parse("'xyz' instanceof T(Integer)");
		assertEquals(false, expression.getValue());
		assertCanCompile(expression);
		assertEquals(false, expression.getValue());

		List<String> list = new ArrayList<>();
		expression = parse("#root instanceof T(java.util.List)");
		assertEquals(true, expression.getValue(list));
		assertCanCompile(expression);
		assertEquals(true, expression.getValue(list));

		List<String>[] arrayOfLists = new List[] {new ArrayList<String>()};
		expression = parse("#root instanceof T(java.util.List[])");
		assertEquals(true, expression.getValue(arrayOfLists));
		assertCanCompile(expression);
		assertEquals(true, expression.getValue(arrayOfLists));

		int[] intArray = new int[] {1,2,3};
		expression = parse("#root instanceof T(int[])");
		assertEquals(true, expression.getValue(intArray));
		assertCanCompile(expression);
		assertEquals(true, expression.getValue(intArray));

		String root = null;
		expression = parse("#root instanceof T(Integer)");
		assertEquals(false, expression.getValue(root));
		assertCanCompile(expression);
		assertEquals(false, expression.getValue(root));

		// root still null
		expression = parse("#root instanceof T(java.lang.Object)");
		assertEquals(false, expression.getValue(root));
		assertCanCompile(expression);
		assertEquals(false, expression.getValue(root));

		root = "howdy!";
		expression = parse("#root instanceof T(java.lang.Object)");
		assertEquals(true, expression.getValue(root));
		assertCanCompile(expression);
		assertEquals(true, expression.getValue(root));
	}
