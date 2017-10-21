    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TextProviderFactory tpf = container.getInstance(TextProviderFactory.class);

        ActionContext.getContext().setLocale(Locale.ENGLISH);

        textProvider = new CompositeTextProvider(new TextProvider[]{
                tpf.createInstance(ResourceBundle.getBundle("com.opensymphony.xwork2.validator.CompositeTextProviderTestResourceBundle1")),
                tpf.createInstance(ResourceBundle.getBundle("com.opensymphony.xwork2.validator.CompositeTextProviderTestResourceBundle2"))
        });
    }
