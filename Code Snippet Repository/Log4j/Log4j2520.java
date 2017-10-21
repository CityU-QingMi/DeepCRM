    @Test
    public void testShortOptionAttachedQuotedParameterQuotesRemovedFromValue() {
        class TextOption {
            @CommandLine.Option(names = "-t") String text;
        }
        TextOption opt = CommandLine.populateCommand(new TextOption(), "-t\"a text\"");
        assertEquals("a text", opt.text);

        opt = CommandLine.populateCommand(new TextOption(), "-t=\"a text\"");
        assertEquals("a text", opt.text);
    }
