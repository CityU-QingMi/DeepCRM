        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    deleteDir();
                    try {
                        base.evaluate();
                    } finally {
                        deleteDir();
                    }
                }
            };
        }
