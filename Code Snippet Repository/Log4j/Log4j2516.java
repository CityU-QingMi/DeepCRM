    @Test
    public void testGnuLongOptionsWithVariousSeparatorsOnlyAndNoValue() {
        VariousPrefixCharacters params;
        try {
            params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--dash".split(" "));
            fail("int option needs arg");
        } catch (ParameterException ex) {
            assertEquals("Missing required parameter for option '-d' (dash)", ex.getMessage());
        }

        try {
            params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--owner".split(" "));
        } catch (ParameterException ex) {
            assertEquals("Missing required parameter for option '/Owner' (owner)", ex.getMessage());
        }

        params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--owner=".split(" "));
        assertEquals("--owner= (at end)", "", params.owner);

        params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--owner= /4".split(" "));
        assertEquals("--owner= (in middle)", "", params.owner);
        assertEquals("/4", true, params.fourDigit);

        try {
            params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--dash=".split(" "));
            fail("int option (with sep but no value) needs arg");
        } catch (ParameterException ex) {
            assertEquals("Could not convert '' to int for option '-d'" +
                    ": java.lang.NumberFormatException: For input string: \"\"", ex.getMessage());
        }

        try {
            params = CommandLine.populateCommand(new VariousPrefixCharacters(), "--dash= /4".split(" "));
            fail("int option (with sep but no value, followed by other option) needs arg");
        } catch (ParameterException ex) {
            assertEquals("Could not convert '' to int for option '-d'" +
                    ": java.lang.NumberFormatException: For input string: \"\"", ex.getMessage());
        }
    }
