	@Test
	public void convertToLong() {
		assertEquals(Long.valueOf(Long.valueOf(-1)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(-1), Long.class));
		assertEquals(Long.valueOf(Long.valueOf(0)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(0), Long.class));
		assertEquals(Long.valueOf(Long.valueOf(1)), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(1), Long.class));
		assertEquals(Long.valueOf(Long.MAX_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Long.MAX_VALUE), Long.class));
		assertEquals(Long.valueOf(Long.MIN_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Long.MAX_VALUE + 1), Long.class));
		assertEquals(Long.valueOf(Long.MIN_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Long.MIN_VALUE), Long.class));
		assertEquals(Long.valueOf(Long.MAX_VALUE), NumberUtils.convertNumberToTargetClass(BigInteger.valueOf(Long.MIN_VALUE - 1), Long.class));

		assertEquals(Long.valueOf(Long.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Long.valueOf(-1), Long.class));
		assertEquals(Long.valueOf(Long.valueOf(0)), NumberUtils.convertNumberToTargetClass(Long.valueOf(0), Long.class));
		assertEquals(Long.valueOf(Long.valueOf(1)), NumberUtils.convertNumberToTargetClass(Long.valueOf(1), Long.class));
		assertEquals(Long.valueOf(Long.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Long.MAX_VALUE), Long.class));
		assertEquals(Long.valueOf(Long.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Long.MAX_VALUE + 1), Long.class));
		assertEquals(Long.valueOf(Long.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Long.MIN_VALUE), Long.class));
		assertEquals(Long.valueOf(Long.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Long.valueOf(Long.MIN_VALUE - 1), Long.class));

		assertEquals(Long.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(-1), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(0), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Integer.valueOf(1), Long.class));
		assertEquals(Long.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MAX_VALUE), Long.class));
		assertEquals(Long.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MAX_VALUE + 1), Long.class));
		assertEquals(Long.valueOf(Integer.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MIN_VALUE), Long.class));
		assertEquals(Long.valueOf(Integer.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Integer.valueOf(Integer.MIN_VALUE - 1), Long.class));

		assertEquals(Long.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) -1), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) 0), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) 1), Long.class));
		assertEquals(Long.valueOf(Short.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf(Short.MAX_VALUE), Long.class));
		assertEquals(Long.valueOf(Short.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) (Short.MAX_VALUE + 1)), Long.class));
		assertEquals(Long.valueOf(Short.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf(Short.MIN_VALUE), Long.class));
		assertEquals(Long.valueOf(Short.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Short.valueOf((short) (Short.MIN_VALUE - 1)), Long.class));

		assertEquals(Long.valueOf(Integer.valueOf(-1)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) -1), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(0)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) 0), Long.class));
		assertEquals(Long.valueOf(Integer.valueOf(1)), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) 1), Long.class));
		assertEquals(Long.valueOf(Byte.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf(Byte.MAX_VALUE), Long.class));
		assertEquals(Long.valueOf(Byte.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) (Byte.MAX_VALUE + 1)), Long.class));
		assertEquals(Long.valueOf(Byte.MIN_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf(Byte.MIN_VALUE), Long.class));
		assertEquals(Long.valueOf(Byte.MAX_VALUE), NumberUtils.convertNumberToTargetClass(Byte.valueOf((byte) (Byte.MIN_VALUE - 1)), Long.class));

		assertToNumberOverflow(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE), Long.class);
		assertToNumberOverflow(BigInteger.valueOf(Long.MIN_VALUE).subtract(BigInteger.ONE), Long.class);
		assertToNumberOverflow(new BigDecimal("18446744073709551611"), Long.class);
	}
