        public Setup(final Class<?> klass, final Runner runner, final String name, final String log4jConfig,
                final int threadCount, final WaitStrategy wait, final String... systemProperties) throws IOException {
            this.klass = klass;
            this.runner = runner;
            this.name = name;
            this.log4jConfig = log4jConfig;
            this.threadCount = threadCount;
            this.systemProperties = systemProperties;
            this.wait = wait;
            this.temp = File.createTempFile("log4jperformance", ".txt");
        }
