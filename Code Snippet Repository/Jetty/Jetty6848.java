    @Test
    public void testPyWebSocket_Client_ToraToraTora()
    {
        Tester tester = clientExtensions.newTester("permessage-deflate; client_max_window_bits");

        tester.assertNegotiated("permessage-deflate");

        // Captured from Pywebsocket (r790) - "tora" sent 3 times.

        tester.parseIncomingHex( // context takeover (3 messages)
                "c1 06 2a c9 2f 4a 04 00", // tora 1
                "c1 05 2a 01 62 00 00", // tora 2
                "c1 04 02 61 00 00" // tora 3
        );

        tester.assertHasFrames("tora", "tora", "tora");
    }
