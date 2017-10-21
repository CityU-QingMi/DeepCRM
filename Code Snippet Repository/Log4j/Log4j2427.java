    @Test
    public void testCatUsageFormat() {
        @Command(name = "cat",
                customSynopsis = "cat [OPTIONS] [FILE...]",
                description = "Concatenate FILE(s), or standard input, to standard output.",
                footer = "Copyright(c) 2017")
        class Cat {
            @Parameters(paramLabel = "FILE", hidden = true, description = "Files whose contents to display") List<File> files;
            @Option(names = "--help",    help = true,     description = "display this help and exit") boolean help;
            @Option(names = "--version", help = true,     description = "output version information and exit") boolean version;
            @Option(names = "-u",                         description = "(ignored)") boolean u;
            @Option(names = "-t",                         description = "equivalent to -vT") boolean t;
            @Option(names = "-e",                         description = "equivalent to -vET") boolean e;
            @Option(names = {"-A", "--show-all"},         description = "equivalent to -vET") boolean showAll;
            @Option(names = {"-s", "--squeeze-blank"},    description = "suppress repeated empty output lines") boolean squeeze;
            @Option(names = {"-v", "--show-nonprinting"}, description = "use ^ and M- notation, except for LDF and TAB") boolean v;
            @Option(names = {"-b", "--number-nonblank"},  description = "number nonempty output lines, overrides -n") boolean b;
            @Option(names = {"-T", "--show-tabs"},        description = "display TAB characters as ^I") boolean T;
            @Option(names = {"-E", "--show-ends"},        description = "display $ at end of each line") boolean E;
            @Option(names = {"-n", "--number"},           description = "number all output lines") boolean n;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        CommandLine.usage(new Cat(), new PrintStream(baos), Help.Ansi.OFF);
        String expected = String.format(
                "Usage: cat [OPTIONS] [FILE...]%n" +
                        "Concatenate FILE(s), or standard input, to standard output.%n" +
                        "  -A, --show-all              equivalent to -vET%n" +
                        "  -b, --number-nonblank       number nonempty output lines, overrides -n%n" +
                        "  -e                          equivalent to -vET%n" +
                        "  -E, --show-ends             display $ at end of each line%n" +
                        "  -n, --number                number all output lines%n" +
                        "  -s, --squeeze-blank         suppress repeated empty output lines%n" +
                        "  -t                          equivalent to -vT%n" +
                        "  -T, --show-tabs             display TAB characters as ^I%n" +
                        "  -u                          (ignored)%n" +
                        "  -v, --show-nonprinting      use ^ and M- notation, except for LDF and TAB%n" +
                        "      --help                  display this help and exit%n" +
                        "      --version               output version information and exit%n" +
                        "Copyright(c) 2017%n", "");
        assertEquals(expected, baos.toString());
    }
