    @Test
    public void testBadURI() throws Exception
    {
        JettyTrackingSocket wsocket = new JettyTrackingSocket();

        try
        {
            client.connect(wsocket,uri); // should toss exception

            Assert.fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e)
        {
            // expected path
            wsocket.assertNotOpened();
        }
    }
