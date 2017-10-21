    public void testDoubleToStringConversionPL() throws Exception {
        // given
        StringConverter converter = new StringConverter();
        Map<String, Object> context = new HashMap<>();
        context.put(ActionContext.LOCALE, new Locale("pl", "PL"));

        // when
        Object value = converter.convertValue(context, null, null, null, 234.12, null);

        // then
        assertEquals("234,12", value);
    }
