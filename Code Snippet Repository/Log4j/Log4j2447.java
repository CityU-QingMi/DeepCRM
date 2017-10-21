    @Test
    public void testSingleValueFieldDefaultMinArityIsOne() {
        try {
            CommandLine.populateCommand(new SupportedTypes(),  "-Long", "-boolean");
            fail("should fail");
        } catch (ParameterException ex) {
            assertEquals("Could not convert '-boolean' to Long for option '-Long'" +
                    ": java.lang.NumberFormatException: For input string: \"-boolean\"", ex.getMessage());
        }
    }
