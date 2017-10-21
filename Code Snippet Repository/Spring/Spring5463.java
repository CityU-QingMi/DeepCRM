	@Test
	public void convertToInteger() {
		assertEquals(Integer.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(-1), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(0), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Integer.MAX_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Integer.MAX_VALUE + 1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Integer.MIN_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Integer.MIN_VALUE - 1), Integer.class));

		assertEquals(Integer.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Long.valueOf(-1), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Long.valueOf(0), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Long.valueOf(1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Integer.MAX_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Integer.MAX_VALUE + 1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Integer.MIN_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Integer.MIN_VALUE - 1), Integer.class));

		assertEquals(Integer.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(-1), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(0), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MAX_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MAX_VALUE + 1), Integer.class));
		assertEquals(Integer.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MIN_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MIN_VALUE - 1), Integer.class));

		assertEquals(Integer.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) -1), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) 0), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) 1), Integer.class));
		assertEquals(Integer.valueOf(Short.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf(Short.MAX_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Short.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) (Short.MAX_VALUE + 1)), Integer.class));
		assertEquals(Integer.valueOf(Short.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf(Short.MIN_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Short.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) (Short.MIN_VALUE - 1)), Integer.class));

		assertEquals(Integer.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) -1), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) 0), Integer.class));
		assertEquals(Integer.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) 1), Integer.class));
		assertEquals(Integer.valueOf(Byte.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf(Byte.MAX_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Byte.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) (Byte.MAX_VALUE + 1)), Integer.class));
		assertEquals(Integer.valueOf(Byte.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf(Byte.MIN_VALUE), Integer.class));
		assertEquals(Integer.valueOf(Byte.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) (Byte.MIN_VALUE - 1)), Integer.class));

		assertToNumberOverflow(Long.valueOf(Long.MAX_VALUE + 1), Integer.class);
		assertToNumberOverflow(Long.valueOf(Long.MIN_VALUE - 1), Integer.class);
		assertToNumberOverflow(BigInteger.valueOf(Integer.MAX_VALUE).add(BigInteger.ONE), Integer.class);
		assertToNumberOverflow(BigInteger.valueOf(Integer.MIN_VALUE).subtract(BigInteger.ONE), Integer.class);
		assertToNumberOverflow(new BigDecimal("18446744073709551611"), Integer.class);
	}
