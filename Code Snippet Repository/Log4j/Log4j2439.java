    @Test
    public void testAnsiEnabled() {
        assertTrue(Help.Ansi.ON.enabled());
        assertFalse(Help.Ansi.OFF.enabled());

        System.setProperty("picocli.ansi", "true");
        assertEquals(true, Help.Ansi.AUTO.enabled());

        System.setProperty("picocli.ansi", "false");
        assertEquals(false, Help.Ansi.AUTO.enabled());

        System.clearProperty("picocli.ansi");
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");
        boolean isXterm   = System.getenv("TERM") != null && System.getenv("TERM").startsWith("xterm");
        boolean isAtty    = (isWindows && isXterm) // cygwin pseudo-tty
                          || hasConsole();
        assertEquals(isAtty && (!isWindows || isXterm), Help.Ansi.AUTO.enabled());
    }
