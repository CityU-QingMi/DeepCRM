    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interceptor = new MyTimerInterceptor();
        interceptor.init();

        mai = new MockActionInvocation();
        ap = new MockActionProxy();
        ap.setActionName("myAction");
        ap.setNamespace("myApp");
        ap.setMethod("execute");
        mai.setAction(new SimpleFooAction());
        mai.setProxy(ap);
    }
