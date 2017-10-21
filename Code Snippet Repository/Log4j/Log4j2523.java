    @Test
    public void testOptionMultiParameterQuotesRemovedFromValue() {
        class TextOption {
            @CommandLine.Option(names = "-t") String[] text;
        }
        TextOption opt = CommandLine.populateCommand(new TextOption(), "-t", "\"a text\"", "\"another text\"", "\"x z\"");
        assertArrayEquals(new String[]{"a text", "another text", "x z"}, opt.text);

        opt = CommandLine.populateCommand(new TextOption(), "-t\"a text\"", "\"another text\"", "\"x z\"");
        assertArrayEquals(new String[]{"a text", "another text", "x z"}, opt.text);

        opt = CommandLine.populateCommand(new TextOption(), "-t=\"a text\"", "\"another text\"", "\"x z\"");
        assertArrayEquals(new String[]{"a text", "another text", "x z"}, opt.text);
    }
