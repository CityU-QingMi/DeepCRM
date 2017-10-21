    @Test
    public void testPyWebSocket_TimeTimeTime()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Pywebsocket (r781) - "time:" sent 3 times.
                "c1876b100104" + "41d9cd49de1201", // "time:"
                "c1852ae3ff01" + "00e2ee012a", // "time:"
                "c18435558caa" + "37468caa" // "time:"
        );

        tester.assertHasFrames("time:", "time:", "time:");
    }
