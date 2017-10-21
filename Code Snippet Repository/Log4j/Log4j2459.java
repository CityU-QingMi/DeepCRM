    private void parseInvalidValue(String option, String value, String errorMessage) {
        try {
            CommandLine.populateCommand(new SupportedTypes(), option, value);
            fail("Invalid format " + value + " was accepted for " + option);
        } catch (ParameterException actual) {
            String type = option.substring(1);
            String expected = "Could not convert '" + value + "' to " + type + " for option '" + option + "'" + errorMessage;
            assertTrue("expected:<" + expected + "> but was:<" + actual.getMessage() + ">",
                    actual.getMessage().startsWith(actual.getMessage()));
        }
    }
