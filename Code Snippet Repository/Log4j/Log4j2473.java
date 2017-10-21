    @Test
    public void testMissingRequiredParams1() {
        class Tricky1 {
            @Parameters(index = "2") String anotherMandatory;
            @Parameters(index = "1", arity = "0..1") String optional;
            @Parameters(index = "0") String mandatory;
        }
        try {
            CommandLine.populateCommand(new Tricky1(), new String[0]);
            fail("Should not accept missing mandatory parameter");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameters: mandatory, anotherMandatory", ex.getMessage());
        }
        try {
            CommandLine.populateCommand(new Tricky1(), new String[] {"firstonly"});
            fail("Should not accept missing mandatory parameter");
        } catch (MissingParameterException ex) {
            assertEquals("Missing required parameter: anotherMandatory", ex.getMessage());
        }
    }
