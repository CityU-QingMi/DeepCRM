    @Test
    public void testDraft21_DeflateBlockWithNoCompression()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex(// 1 message / no compression
                "0xc1 0x0b 0x00 0x05 0x00 0xfa 0xff 0x48 0x65 0x6c 0x6c 0x6f 0x00" // example frame
        );

        tester.assertHasFrames("Hello");
    }
