    @Test
    public void testChrome20_TimeTime()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Chrome 20.x - "time:" then "time:" once more (sent from browser)
                "c18782467424a88fb869374474", // "time:"
                "c1853cfda17f16fcb07f3c" // "time:"
        );

        tester.assertHasFrames("time:", "time:");
    }
