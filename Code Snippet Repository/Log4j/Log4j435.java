    @Test
    public void testIdentityToString() throws Exception {
        final List<Object> list = new ArrayList<>();
        list.add(1);
        list.add(list);
        list.add(2);
        final String actual = ParameterFormatter.identityToString(list);
        final String expected = list.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(list));
        assertEquals(expected, actual);
    }
