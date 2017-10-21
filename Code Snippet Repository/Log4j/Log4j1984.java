        public String description() {
            String detail = klass.getSimpleName();
            if (PerfTest.class == klass) {
                detail = "single thread";
            } else if (MultiThreadPerfTest.class == klass) {
                detail = threadCount + " threads";
            }
            final String target = runner.name();
            return target + ": " + name + " (" + detail + ')';
        }
