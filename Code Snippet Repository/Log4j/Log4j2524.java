    @Test
    public void testSubclassedOptions() {
        class ParentOption {
            @CommandLine.Option(names = "-p") String path;
        }
        class ChildOption extends ParentOption {
            @CommandLine.Option(names = "-t") String text;
        }
        ChildOption opt = CommandLine.populateCommand(new ChildOption(), "-p", "somePath", "-t", "\"a text\"");
        assertEquals("somePath", opt.path);
        assertEquals("a text", opt.text);
    }
