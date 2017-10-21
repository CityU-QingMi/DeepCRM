    @Test
    public void testChrome20_Info()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Chrome 20.x - "info:" (sent from browser)
                "c187ca4def7f0081a4b47d4fef" // example payload 
        );

        tester.assertHasFrames("info:");
    }
