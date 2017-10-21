	@Test
	void isJavaPlatformModuleSystemAvailable() {
		boolean expected;
		try {
			Class.forName("java.lang.Module");
			expected = true;
		}
		catch (ClassNotFoundException e) {
			expected = false;
		}
		assertEquals(expected, ReflectionUtils.isJavaPlatformModuleSystemAvailable());
	}
