    public String stack() {
        ValueStack stack = ActionContext.getContext().getValueStack();
        //DefaultTextProvider, NestedActionTest pushed on by the test, and the NestedAction
        Assert.assertEquals(3, stack.size());
        Assert.assertNotNull(stack.findValue(ActionNestingTest.KEY));
        Assert.assertEquals(ActionContext.getContext().getValueStack().findValue(ActionNestingTest.KEY), ActionNestingTest.VALUE);
        Assert.assertEquals(ActionNestingTest.NESTED_VALUE, stack.findValue(ActionNestingTest.NESTED_KEY));

        return SUCCESS;
    }
