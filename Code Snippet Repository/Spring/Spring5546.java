	@Test
	public void withClasses() {
		assertTrue(TypeUtils.isAssignable(Object.class, Object.class));
		assertTrue(TypeUtils.isAssignable(Object.class, String.class));
		assertFalse(TypeUtils.isAssignable(String.class, Object.class));
		assertTrue(TypeUtils.isAssignable(List.class, List.class));
		assertTrue(TypeUtils.isAssignable(List.class, LinkedList.class));
		assertFalse(TypeUtils.isAssignable(List.class, Collection.class));
		assertFalse(TypeUtils.isAssignable(List.class, HashSet.class));
	}
