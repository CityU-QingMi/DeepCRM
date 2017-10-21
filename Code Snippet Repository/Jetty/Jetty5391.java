    @Test
    public void testThreeMissing()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("10.","1");

        assertNotNull(map.match("10.5.2.0"));
        assertNotNull(map.match("10.5.2.128"));
        assertNotNull(map.match("10.5.2.255"));
        assertNotNull(map.match("10.5.0.1"));
        assertNotNull(map.match("10.5.128.1"));
        assertNotNull(map.match("10.5.255.1"));
        assertNotNull(map.match("10.0.1.1"));
        assertNotNull(map.match("10.128.1.1"));
        assertNotNull(map.match("10.255.1.1"));
    }
