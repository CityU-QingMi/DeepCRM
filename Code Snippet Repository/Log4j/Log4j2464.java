    @Test
    public void testEnumListTypeConversionFailsForInvalidInput() {
        try {
            CommandLine.populateCommand(new EnumParams(), "-timeUnitList", "DAYS", "b", "c");
            fail("Accepted invalid timeunit");
        } catch (Exception ex) {
            assertEquals("Could not convert 'b' to TimeUnit for option '-timeUnitList' at index 1 (timeUnitList)" +
                    ": java.lang.IllegalArgumentException: No enum constant java.util.concurrent.TimeUnit.b",
                    ex.getMessage());
        }
    }
