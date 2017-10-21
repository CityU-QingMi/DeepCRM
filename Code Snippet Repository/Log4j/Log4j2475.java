    @Test
    public void testMissingRequiredParamsWithOptions() {
        class Tricky3 {
            @Option(names="-v") boolean more;
            @Option(names="-t") boolean any;
            @Parameters(index = "1") String alsoMandatory;
            @Parameters(index = "0") String mandatory;
        }
        try {
            CommandLine.populateCommand(new Tricky3(), new String[] {"-t", "-v", "mandatory"});
            fail("Should not accept missing mandatory parameter");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameter: alsoMandatory", ex.getMessage());
        }

        try {
            CommandLine.populateCommand(new Tricky3(), new String[] { "-t", "-v"});
            fail("Should not accept missing two mandatory parameters");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameters: mandatory, alsoMandatory", ex.getMessage());
        }
    }
