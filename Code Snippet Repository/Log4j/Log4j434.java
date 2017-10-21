    @Test
    public void testDeepToString() throws Exception {
        final List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(list);
        list.add(2);
        final String actual = ParameterFormatter.deepToString(list);
        final String expected = "[1, [..." + ParameterFormatter.identityToString(list) + "...], 2]";
        assertEquals(expected, actual);
    }
