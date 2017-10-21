    @Test
    public void testPyWebSocket_Server_ToraToraTora()
    {
        Tester tester = serverExtensions.newTester("permessage-deflate; client_max_window_bits");

        tester.assertNegotiated("permessage-deflate");

        // Captured from Pywebsocket (r790) - "tora" sent 3 times.

        tester.parseIncomingHex( // context takeover (3 messages)
                "c1 86 69 39 fe 91 43 f0  d1 db 6d 39", // tora 1
                "c1 85 2d f3 eb 96 07 f2  89 96 2d", // tora 2
                "c1 84 53 ad a5 34 51 cc  a5 34" // tora 3
        );

        tester.assertHasFrames("tora", "tora", "tora");
    }
