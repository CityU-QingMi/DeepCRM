    @Test
    public void testIntFieldsAreDecimal() {
        try {
            CommandLine.populateCommand(new SupportedTypes(), "-int", "0xFF", "-Integer", "0xFFFF");
            fail("Should fail on hex input");
        } catch (ParameterException expected) {
            assertEquals("Could not convert '0xFF' to int for option '-int'" +
                    ": java.lang.NumberFormatException: For input string: \"0xFF\"", expected.getMessage());
        }
    }
