    @Test
    public void testBlockheadClient_HelloThere()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Blockhead Client - "Hello" then "There" via unit test
                "c18700000000f248cdc9c90700", // "Hello"
                "c187000000000ac9482d4a0500" // "There"
        );

        tester.assertHasFrames("Hello", "There");
    }
