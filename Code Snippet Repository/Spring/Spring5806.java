	@Test
	public void primitiveTypeArrayConstructorsElements() {
		evaluate("new int[]{1,2,3,4}[0]", 1, Integer.class);
		evaluate("new boolean[]{true,false,true}[0]", true, Boolean.class);
		evaluate("new char[]{'a','b','c'}[0]", 'a', Character.class);
		evaluate("new long[]{1,2,3,4,5}[0]", 1L, Long.class);
		evaluate("new short[]{2,3,4,5,6}[0]", (short) 2, Short.class);
		evaluate("new double[]{1d,2d,3d,4d}[0]", (double) 1, Double.class);
		evaluate("new float[]{1f,2f,3f,4f}[0]", (float) 1, Float.class);
		evaluate("new byte[]{1,2,3,4}[0]", (byte) 1, Byte.class);
		evaluate("new String(new char[]{'h','e','l','l','o'})", "hello", String.class);
	}
