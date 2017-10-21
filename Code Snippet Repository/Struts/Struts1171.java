    public void testStringArrayToPrimitives() throws OgnlException {
        long[] longs = (long[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, long[].class);
        assertNotNull(longs);
        assertTrue(Arrays.equals(new long[]{123, 456}, longs));

        int[] ints = (int[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, int[].class);
        assertNotNull(ints);
        assertTrue(Arrays.equals(new int[]{123, 456}, ints));

        double[] doubles = (double[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, double[].class);
        assertNotNull(doubles);
        assertTrue(Arrays.equals(new double[]{123, 456}, doubles));

        float[] floats = (float[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, float[].class);
        assertNotNull(floats);
        assertTrue(Arrays.equals(new float[]{123, 456}, floats));

        boolean[] booleans = (boolean[]) converter.convertValue(context, null, null, null, new String[]{
                "true", "false"
        }, boolean[].class);
        assertNotNull(booleans);
        assertTrue(Arrays.equals(new boolean[]{true, false}, booleans));
    }
