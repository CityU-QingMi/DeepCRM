    @Test
    public void testOneAddress()
    {
        IPAddressMap<String> map = new IPAddressMap<>();

        map.put("10.5.2.1","1");

        assertNotNull(map.match("10.5.2.1"));

        assertNull(map.match("101.5.2.1"));
        assertNull(map.match("10.15.2.1"));
        assertNull(map.match("10.5.22.1"));
        assertNull(map.match("10.5.2.0"));
    }
