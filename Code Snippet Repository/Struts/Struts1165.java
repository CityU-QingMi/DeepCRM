    public void testDateWithLocaleUK() throws Exception {

        Map<String, Object> map = new HashMap<>();
        Locale locale = new Locale("en", "US");
        map.put(ActionContext.LOCALE, locale);

        String reference = "01/09/2009";
        Object convertedObject = basicConverter.convertValue(map, null, null, null, reference, Date.class);

        assertNotNull(convertedObject);

        compareDates(locale, convertedObject);
    }
