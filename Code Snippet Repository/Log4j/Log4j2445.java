    @Test
    public void testSortByOptionArityAndNameComparator_sortsByMaxThenMinThenName() throws Exception {
        class App {
            @Option(names = {"-t", "--aaaa"}) boolean tImplicitArity0;
            @Option(names = {"-e", "--EEE"}, arity = "1") boolean explicitArity1;
            @Option(names = {"--bbbb", "-k"}) boolean kImplicitArity0;
            @Option(names = {"--AAAA", "-a"}) int aImplicitArity1;
            @Option(names = {"--BBBB", "-b"}) String[] bImplicitArity0_n;
            @Option(names = {"--ZZZZ", "-z"}, arity = "1..3") String[] zExplicitArity1_3;
            @Option(names = {"-f", "--ffff"}) boolean fImplicitArity0;
        }
        Field[] fields = fields(App.class, "tImplicitArity0", "explicitArity1", "kImplicitArity0",
                "aImplicitArity1", "bImplicitArity0_n", "zExplicitArity1_3", "fImplicitArity0");
        Arrays.sort(fields, new Help.SortByOptionArityAndNameAlphabetically());
        Field[] expected = fields(App.class,
                "fImplicitArity0",
                "kImplicitArity0",
                "tImplicitArity0",
                "aImplicitArity1",
                "explicitArity1",
                "zExplicitArity1_3",
                "bImplicitArity0_n");
        assertArrayEquals(expected, fields);
    }
