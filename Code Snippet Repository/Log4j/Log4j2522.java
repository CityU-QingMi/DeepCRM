    @Test
    public void testShortOptionQuotedParameterTypeConversion() {
        class TextOption {
            @CommandLine.Option(names = "-t") int[] number;
            @CommandLine.Option(names = "-v", arity = "1") boolean verbose;
        }
        TextOption opt = CommandLine.populateCommand(new TextOption(), "-t", "\"123\"", "-v", "\"true\"");
        assertEquals(123, opt.number[0]);
        assertTrue(opt.verbose);

        opt = CommandLine.populateCommand(new TextOption(), "-t\"123\"", "-v\"true\"");
        assertEquals(123, opt.number[0]);
        assertTrue(opt.verbose);

        opt = CommandLine.populateCommand(new TextOption(), "-t=\"345\"", "-v=\"true\"");
        assertEquals(345, opt.number[0]);
        assertTrue(opt.verbose);
    }
