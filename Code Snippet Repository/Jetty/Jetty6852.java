    @Test
    public void testDraft21_Hello_UnCompressedBlock_Fragmented()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate");

        tester.assertNegotiated("permessage-deflate");

        tester.parseIncomingHex(// basic, 1 block, compressed with 0 compression level (aka, uncompressed).
                // Fragment 1
                "0x41 0x03 0xf2 0x48 0xcd",
                // Fragment 2
                "0x80 0x04 0xc9 0xc9 0x07 0x00");

        tester.assertHasFrames(
                new TextFrame().setPayload("He").setFin(false),
                new ContinuationFrame().setPayload("llo").setFin(true));
    }
