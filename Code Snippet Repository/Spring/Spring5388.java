	@Test
	public void testIsAssignable() {
		assertTrue(ClassUtils.isAssignable(Object.class, Object.class));
		assertTrue(ClassUtils.isAssignable(String.class, String.class));
		assertTrue(ClassUtils.isAssignable(Object.class, String.class));
		assertTrue(ClassUtils.isAssignable(Object.class, Integer.class));
		assertTrue(ClassUtils.isAssignable(Number.class, Integer.class));
		assertTrue(ClassUtils.isAssignable(Number.class, int.class));
		assertTrue(ClassUtils.isAssignable(Integer.class, int.class));
		assertTrue(ClassUtils.isAssignable(int.class, Integer.class));
		assertFalse(ClassUtils.isAssignable(String.class, Object.class));
		assertFalse(ClassUtils.isAssignable(Integer.class, Number.class));
		assertFalse(ClassUtils.isAssignable(Integer.class, double.class));
		assertFalse(ClassUtils.isAssignable(double.class, Integer.class));
	}
