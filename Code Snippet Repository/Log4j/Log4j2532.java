    @Test
    public void testLongFieldsAreDecimal() {
        try {
            CommandLine.populateCommand(new SupportedTypes(), "-long", "0xAABBCC", "-Long", "0xAABBCCDD");
            fail("Should fail on hex input");
        } catch (ParameterException expected) {
            assertEquals("Could not convert '0xAABBCC' to long for option '-long'" +
                    ": java.lang.NumberFormatException: For input string: \"0xAABBCC\"", expected.getMessage());
        }
    }
