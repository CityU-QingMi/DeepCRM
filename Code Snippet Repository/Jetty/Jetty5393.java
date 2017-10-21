    @Test
    public void testManyMixed()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("10.5.2.1","1");
        map.put("1-15.16-31.32-63.64-127","2");
        map.put("1-15,21.10,16-31.0-15,32-63.-55,195-","3");
        map.put("44.99.99.","4");
        map.put("55.99.","5");
        map.put("66.","6");

        assertEquals("1", map.match("10.5.2.1"));

        assertEquals("2", map.match("7.23.39.71"));
        assertEquals("2", map.match("1.16.32.64"));
        assertEquals("2", map.match("15.31.63.127"));

        assertEquals("3", map.match("7.23.39.46"));
        assertEquals("3", map.match("10.20.10.200"));
        assertEquals("3", map.match("21.10.32.255"));
        assertEquals("3", map.match("21.10.15.0"));

        assertEquals("4", map.match("44.99.99.0"));
        assertEquals("5", map.match("55.99.128.1"));
        assertEquals("6", map.match("66.255.1.1"));

        assertNull(map.match("101.5.2.1"));
        assertNull(map.match("10.15.2.1"));
        assertNull(map.match("10.5.22.1"));
        assertNull(map.match("10.5.2.0"));

        assertNull(map.match("16.32.64.96"));
        assertNull(map.match("1.16.32.194"));
        assertNull(map.match("1.16.31.64"));
        assertNull(map.match("1.15.32.64"));
        assertNull(map.match("0.16.32.64"));

        assertNull(map.match("16.15.20.100"));
        assertNull(map.match("15.10.63.100"));
        assertNull(map.match("15.10.64.128"));
        assertNull(map.match("15.11.32.95"));
        assertNull(map.match("16.31.63.128"));
    }
