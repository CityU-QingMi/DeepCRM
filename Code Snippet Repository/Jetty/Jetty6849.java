    @Test
    public void testPyWebSocket_Server_NoContextTakeover_ThreeOra()
    {
        Tester tester = serverExtensions.newTester("permessage-deflate; client_max_window_bits; client_no_context_takeover");

        tester.assertNegotiated("permessage-deflate");

        // Captured from Pywebsocket (r790) - 3 messages with similar parts.

        tester.parseIncomingHex( // context takeover (3 messages)
                "c1 89 88 bc 1b b1 82 75  34 fb 84 bd 79 b1 88", // ToraTora
                "c1 8b 50 86 88 b2 22 aa  41 9d 1a f2 43 b3 42 86 88", // AtoraFlora
                "c1 8b e2 3e 05 53 e8 f6  cd 9a cd 74 09 52 80 3e 05" // PhloraTora
        );

        tester.assertHasFrames("ToraTora", "AtoraFlora", "PhloraTora");
    }
