    public void testWithVariantRequestOnly() throws Exception {
        prepare(I18nInterceptor.DEFAULT_REQUEST_ONLY_PARAMETER, "ja_JP_JP");
        interceptor.intercept(mai);

        assertFalse(mai.getInvocationContext().getParameters().get(I18nInterceptor.DEFAULT_PARAMETER).isDefined()); // should have been removed
        assertNull(session.get(I18nInterceptor.DEFAULT_SESSION_ATTRIBUTE));

        Locale variant = new Locale("ja", "JP", "JP");
        Locale locale = mai.getInvocationContext().getLocale();
        assertNotNull(locale); // should be stored here
        assertEquals(variant, locale);
        assertEquals("JP", locale.getVariant());
    }
