    @Test
    public void testCustomConverter() {
        class Glob {
            public final String glob;
            public Glob(String glob) { this.glob = glob; }
        }
        class App {
            @Parameters Glob globField;
        }
        class GlobConverter implements ITypeConverter<Glob> {
            public Glob convert(String value) throws Exception { return new Glob(value); }
        }
        CommandLine commandLine = new CommandLine(new App());
        commandLine.registerConverter(Glob.class, new GlobConverter());

        String[] args = {"a*glob*pattern"};
        List<CommandLine> parsed = commandLine.parse(args);
        assertEquals("not empty", 1, parsed.size());
        assertTrue(parsed.get(0).getCommand() instanceof App);
        App app = (App) parsed.get(0).getCommand();
        assertEquals(args[0], app.globField.glob);
    }
