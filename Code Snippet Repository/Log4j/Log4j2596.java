    @Test
    public void test1806() throws ParseException {
        final String formatStub = "yyyy-MM-dd'T'HH:mm:ss.SSS";
        final String dateStub = "2001-02-04T12:08:56.235";
        
        for (final Expected1806 trial : Expected1806.values()) {
            final Calendar cal = initializeCalendar(trial.zone);

            final String message = trial.zone.getDisplayName()+";";
            
            DateParser parser = getInstance(formatStub+"X", trial.zone);
            assertEquals(message+trial.one, cal.getTime().getTime(), parser.parse(dateStub+trial.one).getTime()-trial.offset);

            parser = getInstance(formatStub+"XX", trial.zone);
            assertEquals(message+trial.two, cal.getTime(), parser.parse(dateStub+trial.two));

            parser = getInstance(formatStub+"XXX", trial.zone);
            assertEquals(message+trial.three, cal.getTime(), parser.parse(dateStub+trial.three));
        }
    }
