    @Test
    public void testCIDR() throws Exception
    {
        InetAddressSet set = new InetAddressSet();
        
        set.add("10.10.0.0/16");
        set.add("192.0.80.0/22");
        set.add("168.0.0.80/30");
        set.add("abcd:ef00::/24");
        
        assertTrue(set.test(InetAddress.getByName("10.10.0.0")));
        assertTrue(set.test(InetAddress.getByName("10.10.0.1")));
        assertTrue(set.test(InetAddress.getByName("10.10.255.255")));
        assertTrue(set.test(InetAddress.getByName("::ffff:10.10.0.1")));
        assertTrue(set.test(InetAddress.getByName("192.0.80.0")));
        assertTrue(set.test(InetAddress.getByName("192.0.83.1")));
        assertTrue(set.test(InetAddress.getByName("168.0.0.80")));
        assertTrue(set.test(InetAddress.getByName("168.0.0.83")));
        assertTrue(set.test(InetAddress.getByName("abcd:ef00::1")));
        assertTrue(set.test(InetAddress.getByName("abcd:efff::ffff")));
        
        assertFalse(set.test(InetAddress.getByName("10.11.0.0")));
        assertFalse(set.test(InetAddress.getByName("1.2.3.5")));
        assertFalse(set.test(InetAddress.getByName("192.0.84.1")));
        assertFalse(set.test(InetAddress.getByName("168.0.0.84")));
        assertFalse(set.test(InetAddress.getByName("::10.10.0.1")));
        assertFalse(set.test(InetAddress.getByName("abcd:eeff::1")));
        assertFalse(set.test(InetAddress.getByName("abcd:f000::")));
        
        
        set.add("255.255.255.255/32");
        assertTrue(set.test(InetAddress.getByName("255.255.255.255")));
        assertFalse(set.test(InetAddress.getByName("10.11.0.0")));
        
        set.add("0.0.0.0/0");
        assertTrue(set.test(InetAddress.getByName("10.11.0.0")));
        
        // test #1664
        set.add("2.144.0.0/14");
        set.add("2.176.0.0/12");
        set.add("5.22.0.0/17");
        set.add("5.22.192.0/19");
        assertTrue(set.test(InetAddress.getByName("2.144.0.1")));
        assertTrue(set.test(InetAddress.getByName("2.176.0.1")));
        assertTrue(set.test(InetAddress.getByName("5.22.0.1")));
        assertTrue(set.test(InetAddress.getByName("5.22.192.1")));
    }
