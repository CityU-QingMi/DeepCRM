    @Test
    public void testDraft21_Hello_UnCompressedBlock()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex(
                // basic, 1 block, compressed with 0 compression level (aka, uncompressed).
                "0xc1 0x07",  // (HEADER added for this test)
                "0xf2 0x48 0xcd 0xc9 0xc9 0x07 0x00" // example frame from RFC
        );

        tester.assertHasFrames("Hello");
    }
