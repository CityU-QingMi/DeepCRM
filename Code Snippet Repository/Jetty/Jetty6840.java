    @Test
    public void testChrome20_HelloThere()
    {
        Tester tester = serverExtensions.newTester("deflate-frame");

        tester.assertNegotiated("deflate-frame");

        tester.parseIncomingHex(// Captured from Chrome 20.x - "Hello" then "There" (sent from browser)
                "c1877b1971db8951bc12b21e71", // "Hello"
                "c18759edc8f4532480d913e8c8" // There
        );

        tester.assertHasFrames("Hello", "There");
    }
