    @Override
    protected void setUp() throws Exception {
        super.setUp();
        as = new ActionSupport();
        container.inject(as);

        ActionContext.getContext().setLocale(new Locale("da"));

        mas = new MyActionSupport();
        container.inject(mas);
    }
