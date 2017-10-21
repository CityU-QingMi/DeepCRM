    public void testNodelay() throws InterruptedException {
        beginAt("/wait/example1.jsp");

        setTextField("time", "7000");
        submit();
        assertTextPresent("We are processing your request. Please wait.");

        //hit it again
        beginAt("/wait/longProcess1.action?time=1000");
        assertTextPresent("We are processing your request. Please wait.");
    }
