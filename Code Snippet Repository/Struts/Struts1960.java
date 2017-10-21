    public void testCanResolveLocationUsingOgnl() throws Exception {
        TestResult result = new TestResult();

        String location = "/myaction.action";
        Bean bean = new Bean();
        bean.setLocation(location);

        ValueStack stack = ActionContext.getContext().getValueStack();
        stack.push(bean);

        assertEquals(location, stack.findValue("location"));

        result.setLocation("${location}");
        result.execute(actionInvocation);
        assertEquals(location, result.finalLocation);
    }
