    public void testDateWithLocalePoland() throws Exception {

        Map<String, Object> map = new HashMap<>();
        Locale locale = new Locale("pl", "PL");
        map.put(ActionContext.LOCALE, locale);

        String reference = "2009-01-09";
        Object convertedObject = basicConverter.convertValue(map, null, null, null, reference, Date.class);

        assertNotNull(convertedObject);

        compareDates(locale, convertedObject);
    }
