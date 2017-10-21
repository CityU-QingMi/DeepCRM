    @Ignore("")
    @Test
    public void testEnumTypeConversionIsCaseInsensitive() {
        EnumParams params = CommandLine.populateCommand(new EnumParams(),
                "-timeUnit daYS -timeUnitArray hours miNutEs -timeUnitList SEConds MiCROsEconds nanoSEConds".split(" "));
        assertEquals(DAYS, params.timeUnit);
        assertArrayEquals(new TimeUnit[]{HOURS, TimeUnit.MINUTES}, params.timeUnitArray);
        List<TimeUnit> expected = new ArrayList<TimeUnit>(Arrays.asList(TimeUnit.SECONDS, TimeUnit.MICROSECONDS, TimeUnit.NANOSECONDS));
        assertEquals(expected, params.timeUnitList);
    }
