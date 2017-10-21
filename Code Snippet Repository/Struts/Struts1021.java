    public void testSimpleGetTextsWithInjectedTextProvider() throws Exception {
        ActionContext.getContext().setLocale(new Locale("da"));
        MyActionSupport mas = new MyActionSupport();

        TextProvider textProvider = container.getInstance(TextProvider.class, "system");

        assertNotNull(textProvider);

        container.inject(mas);

        checkGetTexts(mas);
    }
