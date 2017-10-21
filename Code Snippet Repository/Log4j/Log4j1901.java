    @Test
    public void testRename1() throws Exception {
        final File file = new File("target/fileRename/fileRename.log");
        try (final PrintStream pos = new PrintStream(file)) {
            for (int i = 0; i < 100; ++i) {
                pos.println("This is line " + i);
            }
        }

        final File dest = new File("target/fileRename/newFile.log");
        final FileRenameAction action = new FileRenameAction(file, dest, false);
        action.execute();
        assertTrue("Renamed file does not exist", dest.exists());
        assertTrue("Old file exists", !file.exists());
    }
