    @Test
    public void testSubclassedOptionsWithShadowedFieldInitializesChildField() {
        class ParentOption {
            @CommandLine.Option(names = "-parentPath") String path;
        }
        class ChildOption extends ParentOption {
            @CommandLine.Option(names = "-childPath") String path;
        }
        ChildOption opt = CommandLine.populateCommand(new ChildOption(), "-childPath", "somePath");
        assertEquals("somePath", opt.path);

        opt = CommandLine.populateCommand(new ChildOption(), "-parentPath", "somePath");
        assertNull(opt.path);
    }
