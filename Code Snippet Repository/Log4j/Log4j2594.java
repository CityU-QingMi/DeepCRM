    @Test
    public void testLang996() throws ParseException {
        final Calendar expected = Calendar.getInstance(NEW_YORK, Locale.US);
        expected.clear();
        expected.set(2014, Calendar.MAY, 14);

        final DateParser fdp = getInstance("ddMMMyyyy", NEW_YORK, Locale.US);        
        assertEquals(expected.getTime(), fdp.parse("14may2014"));
        assertEquals(expected.getTime(), fdp.parse("14MAY2014"));
        assertEquals(expected.getTime(), fdp.parse("14May2014"));
    }
