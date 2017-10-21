    @Test
    public void testOneRange()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("1-15.16-31.32-63.64-127","1");

        assertNotNull(map.match("7.23.39.71"));
        assertNotNull(map.match("1.16.32.64"));
        assertNotNull(map.match("15.31.63.127"));

        assertNull(map.match("16.32.64.128"));
        assertNull(map.match("1.16.32.63"));
        assertNull(map.match("1.16.31.64"));
        assertNull(map.match("1.15.32.64"));
        assertNull(map.match("0.16.32.64"));
    }
