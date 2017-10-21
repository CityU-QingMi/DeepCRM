    @Test
    public void testDraft21_SharingL77SlidingWindow_ContextTakeover()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex( // context takeover (2 messages)
                // message 1
                "0xc1 0x07", // (HEADER added for this test)
                "0xf2 0x48 0xcd 0xc9 0xc9 0x07 0x00",
                // message 2
                "0xc1 0x07", // (HEADER added for this test)
                "0xf2 0x48 0xcd 0xc9 0xc9 0x07 0x00");

        tester.assertHasFrames("Hello", "Hello");
    }
