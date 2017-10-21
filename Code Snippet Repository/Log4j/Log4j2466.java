    @Test
    public void testByteFieldsAreDecimal() {
        try {
            CommandLine.populateCommand(new SupportedTypes(), "-byte", "0x1F", "-Byte", "0x0F");
            fail("Should fail on hex input");
        } catch (ParameterException expected) {
            assertEquals("Could not convert '0x1F' to byte for option '-byte'" +
                    ": java.lang.NumberFormatException: For input string: \"0x1F\"", expected.getMessage());
        }
    }
