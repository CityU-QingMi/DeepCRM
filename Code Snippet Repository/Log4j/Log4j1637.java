        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    System.setProperty(Constants.LOG4J_LOG_EVENT_FACTORY, TestLogEventFactory.class.getName());
                    resetLogEventFactory(new TestLogEventFactory());
                    try {
                        base.evaluate();
                    } finally {
                        System.clearProperty(Constants.LOG4J_LOG_EVENT_FACTORY);
                        resetLogEventFactory(new DefaultLogEventFactory());
                    }
                }

                private void resetLogEventFactory(final LogEventFactory logEventFactory) throws IllegalAccessException {
                    final Field field = FieldUtils.getField(LoggerConfig.class, "LOG_EVENT_FACTORY", true);
                    FieldUtils.removeFinalModifier(field, true);
                    FieldUtils.writeStaticField(field, logEventFactory, false);
                }
            };
        }
