    @Before
    public void before() {
        logger = context.getLogger("LoggerTest");
        loggerChild = context.getLogger("LoggerTest.child");
        loggerGrandchild = context.getLogger("LoggerTest.child.grand");
        //
        app = context.getListAppender("List").clear();
        host = context.getListAppender("HostTest").clear();
        noThrown = context.getListAppender("NoThrowable").clear();
    }
