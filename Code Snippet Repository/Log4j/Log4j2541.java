    @Test
    public void testSplitInParametersArrayWithArity() {
        class Args {
            @Parameters(arity = "2..4", split = ",") String[] values;
        }
        Args args = CommandLine.populateCommand(new Args(), "a,b,c");
        assertArrayEquals(new String[] {"a", "b", "c"}, args.values);

        args = CommandLine.populateCommand(new Args(), "a,b,c", "B", "C");
        assertArrayEquals(new String[] {"a", "b", "c", "B"}, args.values);

        args = CommandLine.populateCommand(new Args(), "a,b,c", "B,C");
        assertArrayEquals(new String[] {"a", "b", "c", "B"}, args.values);

        args = CommandLine.populateCommand(new Args(), "a,b", "A,B,C");
        assertArrayEquals(new String[] {"a", "b", "A", "B"}, args.values);
    }
