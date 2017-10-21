    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activateProp = System.getProperty(UtilTimerStack.ACTIVATE_PROPERTY);
        minTimeProp = System.getProperty(UtilTimerStack.MIN_TIME);

        System.setProperty(UtilTimerStack.ACTIVATE_PROPERTY, "true");
        UtilTimerStack.setActive(true);
        System.setProperty(UtilTimerStack.MIN_TIME, "0");
    }
