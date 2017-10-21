	@TestFactory
	Stream<DynamicTest> toStreamWithPrimitiveArrays() {
		//@formatter:off
		return Stream.of(
				dynamicTest("boolean[]",
						() -> toStreamWithPrimitiveArray(new boolean[] { true, false })),
				dynamicTest("byte[]",
						() -> toStreamWithPrimitiveArray(new byte[] { 0, Byte.MIN_VALUE, Byte.MAX_VALUE })),
				dynamicTest("char[]",
						() -> toStreamWithPrimitiveArray(new char[] { 0, Character.MIN_VALUE, Character.MAX_VALUE })),
				dynamicTest("double[]",
						() -> toStreamWithPrimitiveArray(new double[] { 0, Double.MIN_VALUE, Double.MAX_VALUE })),
				dynamicTest("float[]",
						() -> toStreamWithPrimitiveArray(new float[] { 0, Float.MIN_VALUE, Float.MAX_VALUE })),
				dynamicTest("int[]",
						() -> toStreamWithPrimitiveArray(new int[] { 0, Integer.MIN_VALUE, Integer.MAX_VALUE })),
				dynamicTest("long[]",
						() -> toStreamWithPrimitiveArray(new long[] { 0, Long.MIN_VALUE, Long.MAX_VALUE })),
				dynamicTest("short[]",
						() -> toStreamWithPrimitiveArray(new short[] { 0, Short.MIN_VALUE, Short.MAX_VALUE }))
		);
		//@formatter:on
	}
