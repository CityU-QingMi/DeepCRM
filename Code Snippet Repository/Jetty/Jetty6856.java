    @Test
    public void testDraft21_DeflateBlockWithBFinal1()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex(// 1 message
                "0xc1 0x08", // header
                "0xf3 0x48 0xcd 0xc9 0xc9 0x07 0x00 0x00" // example payload 
        );

        tester.assertHasFrames("Hello");
    }
