	@Test
	public void numerics() {
		checkNumber("2", 2, Integer.class);
		checkNumber("22", 22, Integer.class);
		checkNumber("+22", 22, Integer.class);
		checkNumber("-22", -22, Integer.class);
		checkNumber("2L", 2L, Long.class);
		checkNumber("22l", 22L, Long.class);

		checkNumber("0x1", 1, Integer.class);
		checkNumber("0x1L", 1L, Long.class);
		checkNumber("0xa", 10, Integer.class);
		checkNumber("0xAL", 10L, Long.class);

		checkNumberError("0x", SpelMessage.NOT_AN_INTEGER);
		checkNumberError("0xL", SpelMessage.NOT_A_LONG);
		checkNumberError(".324", SpelMessage.UNEXPECTED_DATA_AFTER_DOT);
		checkNumberError("3.4L", SpelMessage.REAL_CANNOT_BE_LONG);

		checkNumber("3.5f", 3.5f, Float.class);
		checkNumber("1.2e3", 1.2e3d, Double.class);
		checkNumber("1.2e+3", 1.2e3d, Double.class);
		checkNumber("1.2e-3", 1.2e-3d, Double.class);
		checkNumber("1.2e3", 1.2e3d, Double.class);
		checkNumber("1e+3", 1e3d, Double.class);
	}
