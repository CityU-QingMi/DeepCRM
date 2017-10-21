    @Test
    public void testWithInetAddressSet() throws Exception
    {
        IncludeExcludeSet<String,InetAddress> set = new IncludeExcludeSet<>(InetAddressSet.class);   
        assertTrue(set.test(InetAddress.getByName("192.168.0.1")));
     
        set.include("10.10.0.0/16");
        assertFalse(set.test(InetAddress.getByName("192.168.0.1")));
        assertTrue(set.test(InetAddress.getByName("10.10.128.1")));
        
        set.exclude("[::ffff:10.10.128.1]");
        assertFalse(set.test(InetAddress.getByName("10.10.128.1")));

        set.include("[ffff:ff00::]/24");
        assertTrue(set.test(InetAddress.getByName("ffff:ff00::1")));
        assertTrue(set.test(InetAddress.getByName("ffff:ff00::42")));
        
        set.exclude("[ffff:ff00::42]");
        assertTrue(set.test(InetAddress.getByName("ffff:ff00::41")));
        assertFalse(set.test(InetAddress.getByName("ffff:ff00::42")));
        assertTrue(set.test(InetAddress.getByName("ffff:ff00::43")));
        
        
        set.include("192.168.0.0-192.168.255.128");
        assertTrue(set.test(InetAddress.getByName("192.168.0.1")));
        assertTrue(set.test(InetAddress.getByName("192.168.254.255")));
        assertFalse(set.test(InetAddress.getByName("192.168.255.255")));
    }
