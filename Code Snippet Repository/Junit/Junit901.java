	@Test
	void isAssignableTo() {
		assertTrue(ReflectionUtils.isAssignableTo("string", String.class));
		assertTrue(ReflectionUtils.isAssignableTo("string", CharSequence.class));
		assertTrue(ReflectionUtils.isAssignableTo("string", Object.class));

		assertFalse(ReflectionUtils.isAssignableTo(new Object(), String.class));
		assertFalse(ReflectionUtils.isAssignableTo(Integer.valueOf("1"), StringBuilder.class));
		assertFalse(ReflectionUtils.isAssignableTo(new StringBuilder(), String.class));

		assertTrue(ReflectionUtils.isAssignableTo(new int[0], int[].class));
		assertTrue(ReflectionUtils.isAssignableTo(new double[0], Object.class));
		assertTrue(ReflectionUtils.isAssignableTo(new String[0], String[].class));
		assertTrue(ReflectionUtils.isAssignableTo(new String[0], Object.class));

		assertTrue(ReflectionUtils.isAssignableTo(1, int.class));
		assertTrue(ReflectionUtils.isAssignableTo(Long.valueOf("1"), long.class));
		assertTrue(ReflectionUtils.isAssignableTo(Boolean.TRUE, boolean.class));

		assertFalse(ReflectionUtils.isAssignableTo(1, char.class));
		assertFalse(ReflectionUtils.isAssignableTo(1L, byte.class));
		assertFalse(ReflectionUtils.isAssignableTo(1L, int.class));
	}
