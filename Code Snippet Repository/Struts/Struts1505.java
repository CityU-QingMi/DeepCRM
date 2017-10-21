    @Override
    protected void tearDown() throws Exception {

        if (activateProp != null) {
            System.setProperty(UtilTimerStack.ACTIVATE_PROPERTY, activateProp);
        } else {
            System.clearProperty(UtilTimerStack.ACTIVATE_PROPERTY);
        }
        if (minTimeProp != null) {
            System.setProperty(UtilTimerStack.MIN_TIME, minTimeProp);
        } else {
            System.clearProperty(UtilTimerStack.ACTIVATE_PROPERTY);
        }


        activateProp = null;
        minTimeProp = null;

        super.tearDown();
    }
