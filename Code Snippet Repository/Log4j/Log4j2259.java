        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    final File logFile = new File("target",
                        description.getClassName() + '.' + description.getMethodName() + ".log");
                    ThreadContext.put("testClassName", description.getClassName());
                    ThreadContext.put("testMethodName", description.getMethodName());
                    try {
                        base.evaluate();
                    } finally {
                        ThreadContext.remove("testClassName");
                        ThreadContext.remove("testMethodName");
                        if (logFile.exists()) {
                            logFile.deleteOnExit();
                        }
                    }
                }
            };
        }
