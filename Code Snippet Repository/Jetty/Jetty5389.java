    @Test
    public void testOneMissing()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("10.5.2.","1");

        assertNotNull(map.match("10.5.2.0"));
        assertNotNull(map.match("10.5.2.128"));
        assertNotNull(map.match("10.5.2.255"));
    }
