    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ActionContext ctx = new ActionContext(new HashMap<String, Object>());
        ActionContext.setContext(ctx);
        ctx.setLocale(Locale.CANADA);

        container.getInstance(LocalizedTextProvider.class).addDefaultResourceBundle(DefaultTextProviderTest.class.getName());

        tp = container.inject(DefaultTextProvider.class);
    }
