    @Test
    public void testArityGreaterThanOneForSingleValuedFields() {
        class Arity2 {
            @CommandLine.Option(names = "-p", arity="2") String path;
            @CommandLine.Option(names = "-o", arity="2") String[] otherPath;
        }
        Arity2 opt = CommandLine.populateCommand(new Arity2(), "-o a b".split(" "));

        try {
            opt = CommandLine.populateCommand(new Arity2(), "-p a b".split(" "));
            fail("expected exception");
        } catch (UnmatchedArgumentException ex) {
            assertEquals("Unmatched argument [b]", ex.getMessage());
        }
    }
