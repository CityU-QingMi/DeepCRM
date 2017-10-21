    private void processInlineBlock(
            Token t, String ifCmdText, String nestingText)
            throws BadSpecial, IOException, SqlToolError  {
        assert t.nestedBlock == null:
            "Inline-nest command has .nestBlock pre-populated";
        SqlFileScanner storedScanner = scanner;
        try {
            scanner = new SqlFileScanner(new StringReader(nestingText + '\n'));
            scanner.setStdPrintStream(shared.psStd);
            scanner.setRawLeadinPrompt("");
            scanner.setInteractive(interactive);
            t.nestedBlock = seekTokenSource(null);
        } finally {
            scanner = storedScanner;
        }
        t.val = ifCmdText;
    }
