	@Test
	public void withParameterizedTypes() throws Exception {
		Type objectsType = getClass().getField("objects").getGenericType();
		Type openObjectsType = getClass().getField("openObjects").getGenericType();
		Type stringsType = getClass().getField("strings").getGenericType();
		assertTrue(TypeUtils.isAssignable(Object.class, objectsType));
		assertTrue(TypeUtils.isAssignable(Object.class, openObjectsType));
		assertTrue(TypeUtils.isAssignable(Object.class, stringsType));
		assertTrue(TypeUtils.isAssignable(List.class, objectsType));
		assertTrue(TypeUtils.isAssignable(List.class, openObjectsType));
		assertTrue(TypeUtils.isAssignable(List.class, stringsType));
		assertTrue(TypeUtils.isAssignable(objectsType, List.class));
		assertTrue(TypeUtils.isAssignable(openObjectsType, List.class));
		assertTrue(TypeUtils.isAssignable(stringsType, List.class));
		assertTrue(TypeUtils.isAssignable(objectsType, objectsType));
		assertTrue(TypeUtils.isAssignable(openObjectsType, openObjectsType));
		assertTrue(TypeUtils.isAssignable(stringsType, stringsType));
		assertTrue(TypeUtils.isAssignable(openObjectsType, objectsType));
		assertTrue(TypeUtils.isAssignable(openObjectsType, stringsType));
		assertFalse(TypeUtils.isAssignable(stringsType, objectsType));
		assertFalse(TypeUtils.isAssignable(objectsType, stringsType));
	}
