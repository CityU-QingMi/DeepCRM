    @Test
    public void testLegacy() throws Exception
    {
        InetAddressSet set = new InetAddressSet();
        
        set.add("10.-.245-.-2");
        set.add("11.11.11.127-129");

        assertFalse(set.test(InetAddress.getByName("9.0.245.0")));
        
        assertTrue(set.test(InetAddress.getByName("10.0.245.0")));
        assertTrue(set.test(InetAddress.getByName("10.0.245.1")));
        assertTrue(set.test(InetAddress.getByName("10.0.245.2")));
        assertFalse(set.test(InetAddress.getByName("10.0.245.3")));

        assertTrue(set.test(InetAddress.getByName("10.255.255.0")));
        assertTrue(set.test(InetAddress.getByName("10.255.255.1")));
        assertTrue(set.test(InetAddress.getByName("10.255.255.2")));
        assertFalse(set.test(InetAddress.getByName("10.255.255.3")));
        
        assertFalse(set.test(InetAddress.getByName("10.0.244.0")));
        assertFalse(set.test(InetAddress.getByName("10.0.244.1")));
        assertFalse(set.test(InetAddress.getByName("10.0.244.2")));
        assertFalse(set.test(InetAddress.getByName("10.0.244.3")));
    }
