	@Test
	public void withGenericArrayTypes() throws Exception {
		Type arrayType = getClass().getField("array").getGenericType();
		Type openArrayType = getClass().getField("openArray").getGenericType();
		assertTrue(TypeUtils.isAssignable(Object.class, arrayType));
		assertTrue(TypeUtils.isAssignable(Object.class, openArrayType));
		assertTrue(TypeUtils.isAssignable(List[].class, arrayType));
		assertTrue(TypeUtils.isAssignable(List[].class, openArrayType));
		assertTrue(TypeUtils.isAssignable(arrayType, List[].class));
		assertTrue(TypeUtils.isAssignable(openArrayType, List[].class));
		assertTrue(TypeUtils.isAssignable(arrayType, arrayType));
		assertTrue(TypeUtils.isAssignable(openArrayType, openArrayType));
		assertTrue(TypeUtils.isAssignable(openArrayType, arrayType));
	}
