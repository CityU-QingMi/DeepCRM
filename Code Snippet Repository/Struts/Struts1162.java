    public void testArrayOfNullToStringConversion() {
        // given
        StringConverter converter = new StringConverter();
        Map<String, Object> context = new HashMap<>();
        context.put(ActionContext.LOCALE, new Locale("pl", "PL"));

        // when
        Object value = converter.convertValue(context, null, null, null, new String[] {null}, null);

        // then
        assertEquals("", value);
   }
