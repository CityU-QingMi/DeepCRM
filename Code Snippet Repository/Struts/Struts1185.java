    public void testCollectionConversion() throws Exception {
        // given
        String[] col1 = new String[]{"1", "2", "ble", "3"};

        // when
        Object converted = converter.convertValue(context, new ListAction(), null, "ints", col1, List.class);

        // then
        assertEquals(converted, Arrays.asList(1, 2, 3));
    }
