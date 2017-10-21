    @Test
    public void testSingleton() throws Exception
    {
        InetAddressSet set = new InetAddressSet();
        
        set.add("webtide.com");
        set.add("1.2.3.4");
        set.add("::abcd");
        
        assertTrue(set.test(InetAddress.getByName("webtide.com")));
        assertTrue(set.test(InetAddress.getByName(InetAddress.getByName("webtide.com").getHostAddress())));
        assertTrue(set.test(InetAddress.getByName("1.2.3.4")));
        assertTrue(set.test(InetAddress.getByAddress(new byte[]{(byte)1,(byte)2,(byte)3,(byte)4})));
        assertTrue(set.test(InetAddress.getByAddress("hostname",new byte[]{(byte)1,(byte)2,(byte)3,(byte)4})));
        assertTrue(set.test(InetAddress.getByName("::0:0:abcd")));
        assertTrue(set.test(InetAddress.getByName("::abcd")));
        assertTrue(set.test(InetAddress.getByName("[::abcd]")));
        assertTrue(set.test(InetAddress.getByName("::ffff:1.2.3.4")));
        
        assertFalse(set.test(InetAddress.getByName("www.google.com")));
        assertFalse(set.test(InetAddress.getByName("1.2.3.5")));
        assertFalse(set.test(InetAddress.getByAddress(new byte[]{(byte)1,(byte)2,(byte)3,(byte)5})));
        assertFalse(set.test(InetAddress.getByAddress("webtide.com",new byte[]{(byte)1,(byte)2,(byte)3,(byte)5})));
        assertFalse(set.test(InetAddress.getByName("::1.2.3.4")));
        assertFalse(set.test(InetAddress.getByName("::1234")));
        assertFalse(set.test(InetAddress.getByName("::abce")));
        assertFalse(set.test(InetAddress.getByName("1::abcd")));
    }
