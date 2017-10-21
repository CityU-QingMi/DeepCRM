    @Test
    public void testOneMixed()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("0-15,21.10,16-31.0-15,32-63.-95,128-","1");

        assertNotNull(map.match("7.23.39.46"));
        assertNotNull(map.match("10.20.10.150"));
        assertNotNull(map.match("21.10.32.255"));
        assertNotNull(map.match("21.10.15.0"));

        assertNull(map.match("16.15.20.100"));
        assertNull(map.match("15.10.63.100"));
        assertNull(map.match("15.10.64.128"));
        assertNull(map.match("15.11.32.95"));
        assertNull(map.match("16.31.63.128"));
    }
