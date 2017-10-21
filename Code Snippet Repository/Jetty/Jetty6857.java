    @Test
    public void testDraft21_TwoDeflateBlocksOneMessage()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex(// 1 message, 1 frame, 2 deflate blocks
                "0xc1 0x0d", // (HEADER added for this test)
                "0xf2 0x48 0x05 0x00 0x00 0x00 0xff 0xff 0xca 0xc9 0xc9 0x07 0x00"
        );

        tester.assertHasFrames("Hello");
    }
