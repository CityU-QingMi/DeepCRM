    @Test
    public void testMissingRequiredParams() {
        class Example {
            @Parameters(index = "1", arity = "0..1") String optional;
            @Parameters(index = "0") String mandatory;
        }
        try { CommandLine.populateCommand(new Example(), new String[] {"mandatory"}); }
        catch (MissingParameterException ex) { fail(); }

        try {
            CommandLine.populateCommand(new Example(), new String[0]);
            fail("Should not accept missing mandatory parameter");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameter: mandatory", ex.getMessage());
        }
    }
