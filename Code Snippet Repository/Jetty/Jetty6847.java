    @Test
    public void testPyWebSocket_Client_NoContextTakeover_ThreeOra()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate; client_max_window_bits; client_no_context_takeover");

        tester.assertNegotiated("permessage-deflate");

        // Captured from Pywebsocket (r790) - 3 messages with similar parts.

        tester.parseIncomingHex( // context takeover (3 messages)
                "c1 09 0a c9 2f 4a 0c 01  62 00 00", // ToraTora
                "c1 0b 72 2c c9 2f 4a 74  cb 01 12 00 00", // AtoraFlora
                "c1 0b 0a c8 c8 c9 2f 4a  0c 01 62 00 00" // PhloraTora
        );

        tester.assertHasFrames("ToraTora", "AtoraFlora", "PhloraTora");
    }
