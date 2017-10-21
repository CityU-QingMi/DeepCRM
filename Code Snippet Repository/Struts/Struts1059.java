    @Override
    protected void setUp() throws Exception {
        super.setUp();
        rb = ResourceBundle.getBundle(TextProviderSupportTest.class.getName(), Locale.ENGLISH);

        LocalizedTextProvider ltu = container.getInstance(LocalizedTextProvider.class);

        tp = new TextProviderSupport(rb, new LocaleProvider() {
            public Locale getLocale() {
                return Locale.ENGLISH;
            }

            @Override
            public boolean isValidLocaleString(String localeStr) {
                return true;
            }

            @Override
            public boolean isValidLocale(Locale locale) {
                return true;
            }
        }, ltu);
    }
