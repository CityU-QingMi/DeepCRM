    @Test
    public void testCommandLine_printVersionInfo_printsArrayOfStringsWithMarkup() throws Exception {
        @Command(version = {
                "@|yellow Versioned Command 1.0|@",
                "@|blue Build 12345|@",
                "@|red,bg(white) (c) 2017|@" })
        class Versioned {}
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        new CommandLine(new Versioned()).printVersionHelp(new PrintStream(baos, true, "UTF8"), Help.Ansi.ON);
        String result = baos.toString("UTF8");
        assertEquals(String.format("" +
                "\u001B[33mVersioned Command 1.0\u001B[39m\u001B[0m%n" +
                "\u001B[34mBuild 12345\u001B[39m\u001B[0m%n" +
                "\u001B[31m\u001B[47m(c) 2017\u001B[49m\u001B[39m\u001B[0m%n"), result);
    }
