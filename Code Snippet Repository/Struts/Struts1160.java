    public void testBigDecimalToStringConversionPL() throws Exception {
        // given
        StringConverter converter = new StringConverter();
        Map<String, Object> context = new HashMap<>();
        context.put(ActionContext.LOCALE, new Locale("pl", "PL"));

        // when
        Object value = converter.convertValue(context, null, null, null, BigDecimal.valueOf(234.12), null);

        // then
        assertEquals("234,12", value);
    }
