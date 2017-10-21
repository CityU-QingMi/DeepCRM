    @Test
    public void testEmpty() throws Exception {
        final File file = new File("target/fileRename/fileRename.log");
        try (final PrintStream pos = new PrintStream(file)) {
            // do nothing
        }

        final File dest = new File("target/fileRename/newFile.log");
        final FileRenameAction action = new FileRenameAction(file, dest, false);
        action.execute();
        assertTrue("Renamed file does not exist", !dest.exists());
        assertTrue("Old file does not exist", !file.exists());
    }
