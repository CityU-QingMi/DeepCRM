    @Test
    public void testLang303() throws ParseException {
        DateParser parser = getInstance(YMD_SLASH);
        final Calendar cal = Calendar.getInstance();
        cal.set(2004, Calendar.DECEMBER, 31);

        final Date date = parser.parse("2004/11/31");

        parser = SerializationUtils.deserialize(SerializationUtils.serialize((Serializable) parser));
        assertEquals(date, parser.parse("2004/11/31"));
    }
