    public Statement apply(Statement base, Description d) {
        final String methodName = d.getMethodName();
        final String testName = methodName == null ? d.getClassName() : d.getClassName() + "#" + d.getMethodName();
        runningTest = testName;
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                TestResourceTracker.testStarted( testName );
                try {
                    base.evaluate();
                } finally {
                    TestResourceTracker.testFinished( testName );
                }
            }
        };
    }
