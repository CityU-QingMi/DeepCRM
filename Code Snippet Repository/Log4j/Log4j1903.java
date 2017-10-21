    @Test
    public void testNoParent() throws Exception {
        final File file = new File("fileRename.log");
        try (final PrintStream pos = new PrintStream(file)) {
            for (int i = 0; i < 100; ++i) {
                pos.println("This is line " + i);
            }
        }

        final File dest = new File("newFile.log");
        try {
            final FileRenameAction action = new FileRenameAction(file, dest, false);
            action.execute();
            assertTrue("Renamed file does not exist", dest.exists());
            assertTrue("Old file exists", !file.exists());
        } finally {
            try {
                dest.delete();
                file.delete();
            } catch (final Exception ex) {
                System.out.println("Unable to cleanup files written to main directory");
            }
        }
    }
