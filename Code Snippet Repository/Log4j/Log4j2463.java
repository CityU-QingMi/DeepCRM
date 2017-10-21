    @Test
    public void testEnumArrayTypeConversionFailsForInvalidInput() {
        try {
            CommandLine.populateCommand(new EnumParams(), "-timeUnitArray", "a", "b");
            fail("Accepted invalid timeunit");
        } catch (Exception ex) {
            assertEquals("Could not convert 'a' to TimeUnit[] for option '-timeUnitArray' at index 0 (timeUnitArray)" +
                    ": java.lang.IllegalArgumentException: No enum constant java.util.concurrent.TimeUnit.a", ex.getMessage());
        }
    }
