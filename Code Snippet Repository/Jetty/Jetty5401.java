    @Test
    public void testInetAddress() throws Exception
    {
        assertTrue(InetAddress.getByName("127.0.0.1").isLoopbackAddress());
        assertTrue(InetAddress.getByName("::1").isLoopbackAddress());
        assertTrue(InetAddress.getByName("::0.0.0.1").isLoopbackAddress());
        assertTrue(InetAddress.getByName("[::1]").isLoopbackAddress());
        assertTrue(InetAddress.getByName("[::0.0.0.1]").isLoopbackAddress());
        assertTrue(InetAddress.getByName("[::ffff:127.0.0.1]").isLoopbackAddress());
    }
