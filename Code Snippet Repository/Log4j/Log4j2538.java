    @Test
    public void testSplitInOptionArrayWithArity() {
        class Args {
            @Option(names = "-a", split = ",", arity = "0..4") String[] values;
            @Parameters() String[] params;
        }
        Args args = CommandLine.populateCommand(new Args(), "-a=a,b,c");
        assertArrayEquals(new String[] {"a", "b", "c"}, args.values);

        args = CommandLine.populateCommand(new Args(), "-a=a,b,c", "B", "C");
        assertArrayEquals(new String[] {"a", "b", "c", "B"}, args.values);
        assertArrayEquals(new String[] {"C"}, args.params);

        args = CommandLine.populateCommand(new Args(), "-a", "a,b,c", "B", "C");
        assertArrayEquals(new String[] {"a", "b", "c", "B"}, args.values);
        assertArrayEquals(new String[] {"C"}, args.params);

        args = CommandLine.populateCommand(new Args(), "-a=a,b,c", "B", "C", "D,E,F");
        assertArrayEquals(new String[] {"a", "b", "c", "B"}, args.values);
        assertArrayEquals(new String[] {"C", "D,E,F"}, args.params);

        args = CommandLine.populateCommand(new Args(), "-a=a,b,c,d,e", "B", "C", "D,E,F");
        assertArrayEquals(new String[] {"a", "b", "c", "d"}, args.values);
        assertArrayEquals(new String[] {"e", "B", "C", "D,E,F"}, args.params);
    }
