    @Test
    public void testMinMax() throws Exception
    {
        InetAddressSet set = new InetAddressSet();
        
        set.add("10.0.0.4-10.0.0.6");
        set.add("10.1.0.254-10.1.1.1");
        set.add("[abcd:ef::fffe]-[abcd:ef::1:1]");

        assertFalse(set.test(InetAddress.getByName("10.0.0.3")));
        assertTrue(set.test(InetAddress.getByName("10.0.0.4")));
        assertTrue(set.test(InetAddress.getByName("10.0.0.5")));
        assertTrue(set.test(InetAddress.getByName("10.0.0.6")));
        assertFalse(set.test(InetAddress.getByName("10.0.0.7")));
        
        assertFalse(set.test(InetAddress.getByName("10.1.0.253")));
        assertTrue(set.test(InetAddress.getByName("10.1.0.254")));
        assertTrue(set.test(InetAddress.getByName("10.1.0.255")));
        assertTrue(set.test(InetAddress.getByName("10.1.1.0")));
        assertTrue(set.test(InetAddress.getByName("10.1.1.1")));
        assertFalse(set.test(InetAddress.getByName("10.1.1.2")));
        
        assertFalse(set.test(InetAddress.getByName("ABCD:EF::FFFD")));
        assertTrue(set.test(InetAddress.getByName("ABCD:EF::FFFE")));
        assertTrue(set.test(InetAddress.getByName("ABCD:EF::FFFF")));
        assertTrue(set.test(InetAddress.getByName("ABCD:EF::1:0")));
        assertTrue(set.test(InetAddress.getByName("ABCD:EF::1:1")));
        assertFalse(set.test(InetAddress.getByName("ABCD:EF::1:2")));
    }
