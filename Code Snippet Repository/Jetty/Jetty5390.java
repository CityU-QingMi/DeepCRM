    @Test
    public void testTwoMissing()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("10.5.","1");

        assertNotNull(map.match("10.5.2.0"));
        assertNotNull(map.match("10.5.2.128"));
        assertNotNull(map.match("10.5.2.255"));
        assertNotNull(map.match("10.5.0.1"));
        assertNotNull(map.match("10.5.128.1"));
        assertNotNull(map.match("10.5.255.1"));
    }
