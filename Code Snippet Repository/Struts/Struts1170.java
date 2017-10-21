    public void testStringArrayToPrimitiveWrappers() {
        Long[] longs = (Long[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, Long[].class);
        assertNotNull(longs);
        assertTrue(Arrays.equals(new Long[]{123L, 456L}, longs));

        Integer[] ints = (Integer[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, Integer[].class);
        assertNotNull(ints);
        assertTrue(Arrays.equals(new Integer[]{123, 456}, ints));

        Double[] doubles = (Double[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, Double[].class);
        assertNotNull(doubles);
        assertTrue(Arrays.equals(new Double[]{123D, 456D}, doubles));

        Float[] floats = (Float[]) converter.convertValue(context, null, null, null, new String[]{
                "123", "456"
        }, Float[].class);
        assertNotNull(floats);
        assertTrue(Arrays.equals(new Float[]{123F, 456F}, floats));

        Boolean[] booleans = (Boolean[]) converter.convertValue(context, null, null, null, new String[]{
                "true", "false"
        }, Boolean[].class);
        assertNotNull(booleans);
        assertTrue(Arrays.equals(new Boolean[]{Boolean.TRUE, Boolean.FALSE}, booleans));
    }
