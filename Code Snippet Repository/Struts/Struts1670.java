    public void execute(ActionInvocation invocation) throws Exception {
        LOG.info("executing TestResult.");

        if ((expectedValues != null) && (expectedValues.size() > 0) && (propertyNames != null) && (propertyNames.size() > 0))
        {
            ValueStack stack = ActionContext.getContext().getValueStack();

            for (int i = 0; i < propertyNames.size(); i++) {
                String propertyName = (String) propertyNames.get(i);
                String expectedValue = null;

                if (i < expectedValues.size()) {
                    expectedValue = (String) expectedValues.get(i);
                }

                String value = (String) stack.findValue(propertyName, String.class);
                Assert.assertEquals(expectedValue, value);
            }
        } else {
            LOG.error("One of expectedValues = " + expectedValues + " and propertyNames = " + propertyNames + " was null or empty.");
            Assert.fail();
        }
    }
