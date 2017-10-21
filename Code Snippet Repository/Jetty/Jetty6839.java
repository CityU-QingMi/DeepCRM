    @Test
    public void testChrome20_Hello()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Chrome 20.x - "Hello" (sent from browser)
                "c187832b5c11716391d84a2c5c" // "Hello"
        );

        tester.assertHasFrames("Hello");
    }
