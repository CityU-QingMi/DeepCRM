    public void testDateWithLocaleFrance() throws Exception {

        Map<String, Object> map = new HashMap<>();
        Locale locale = new Locale("fr", "FR");
        map.put(ActionContext.LOCALE, locale);

        String reference = "09/01/2009";
        Object convertedObject = basicConverter.convertValue(map, null, null, null, reference, Date.class);

        assertNotNull(convertedObject);

        compareDates(locale, convertedObject);
    }
