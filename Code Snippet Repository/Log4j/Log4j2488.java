    @Test
    public void testShortFieldsAreDecimal() {
        try {
            CommandLine.populateCommand(new SupportedTypes(), "-short", "0xFF", "-Short", "0x6FFE");
            fail("Should fail on hex input");
        } catch (ParameterException expected) {
            assertEquals("Could not convert '0xFF' to short for option '-short'" +
                    ": java.lang.NumberFormatException: For input string: \"0xFF\"", expected.getMessage());
        }
    }
