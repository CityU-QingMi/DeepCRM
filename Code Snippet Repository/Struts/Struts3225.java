    @Override
    protected void setUp() throws Exception {
        this.errors = new ArrayList<String>();
        this.errors.add("this clas is bad");
        this.errors.add("baaaaad");

        //errors are needed to setup stack
        super.setUp();
        this.tag = new ActionError(stack, request, response);
    }
