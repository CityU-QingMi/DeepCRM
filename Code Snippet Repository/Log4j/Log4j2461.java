    @Test
    public void testEnumTypeConversionFailsForInvalidInput() {
        try {
            CommandLine.populateCommand(new EnumParams(), "-timeUnit", "xyz");
            fail("Accepted invalid timeunit");
        } catch (Exception ex) {
            assertEquals("Could not convert 'xyz' to TimeUnit for option '-timeUnit'" +
                    ": java.lang.IllegalArgumentException: No enum constant java.util.concurrent.TimeUnit.xyz", ex.getMessage());
        }
    }
