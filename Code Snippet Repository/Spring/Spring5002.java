	@Test
	public void testResolveType() {
		Method intMessageMethod = findMethod(MyTypeWithMethods.class, "readIntegerInputMessage", MyInterfaceType.class);
		MethodParameter intMessageMethodParam = new MethodParameter(intMessageMethod, 0);
		assertEquals(MyInterfaceType.class,
				resolveType(intMessageMethodParam.getGenericParameterType(), new HashMap<>()));

		Method intArrMessageMethod = findMethod(MyTypeWithMethods.class, "readIntegerArrayInputMessage",
				MyInterfaceType[].class);
		MethodParameter intArrMessageMethodParam = new MethodParameter(intArrMessageMethod, 0);
		assertEquals(MyInterfaceType[].class,
				resolveType(intArrMessageMethodParam.getGenericParameterType(), new HashMap<>()));

		Method genericArrMessageMethod = findMethod(MySimpleTypeWithMethods.class, "readGenericArrayInputMessage",
				Object[].class);
		MethodParameter genericArrMessageMethodParam = new MethodParameter(genericArrMessageMethod, 0);
		Map<TypeVariable, Type> varMap = getTypeVariableMap(MySimpleTypeWithMethods.class);
		assertEquals(Integer[].class, resolveType(genericArrMessageMethodParam.getGenericParameterType(), varMap));
	}
