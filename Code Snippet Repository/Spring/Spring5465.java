	private void assertToNumberOverflow(Number number, Class<? extends Number> targetClass) {
		String msg = "Expected exception due to overflow: from=" + number + ", toClass=" + targetClass;
		try {
			NumberUtils.convertNumberToTargetClass(number, targetClass);
			fail(msg);
		}
		catch (IllegalArgumentException expected) {
			assertTrue(msg + ", with \"overflow\" in message but got message=" + expected.getMessage(),
					expected.getMessage().endsWith("overflow"));
		}
	}
