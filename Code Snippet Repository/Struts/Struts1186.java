    @Override
    protected void setUp() throws Exception {
        super.setUp();

        converter = container.getInstance(XWorkConverter.class);

        ActionContext ac = ActionContext.getContext();
        ac.setLocale(Locale.US);
        context = ac.getContextMap();
        stack = (OgnlValueStack) ac.getValueStack();
    }
