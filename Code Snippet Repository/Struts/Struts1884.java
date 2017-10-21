    public void testWithVariant() throws Exception {
        prepare(I18nInterceptor.DEFAULT_PARAMETER, "ja_JP_JP");
        interceptor.intercept(mai);

        assertFalse(mai.getInvocationContext().getParameters().get(I18nInterceptor.DEFAULT_PARAMETER).isDefined()); // should have been removed

        Locale variant = new Locale("ja", "JP", "JP");
        Locale locale = (Locale) session.get(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE);
        assertNotNull(locale); // should be stored here
        assertEquals(variant, locale);
        assertEquals("JP", locale.getVariant());
    }
