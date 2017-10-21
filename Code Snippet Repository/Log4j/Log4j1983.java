        List<String> processArguments(final String java) {
            final List<String> args = new ArrayList<>();
            args.add(java);
            args.add("-server");
            args.add("-Xms1g");
            args.add("-Xmx1g");

            // args.add("-XX:+UseParallelOldGC");
            // args.add("-Xloggc:gc.log");
            // args.add("-XX:+PrintGCTimeStamps");
            // args.add("-XX:+PrintGCDetails");
            // args.add("-XX:+PrintGCDateStamps");
            // args.add("-XX:+PrintGCApplicationStoppedTime");
            // args.add("-XX:+PrintGCApplicationConcurrentTime");
            // args.add("-XX:+PrintSafepointStatistics");

            args.add("-Dlog4j.configuration=" + log4jConfig); // log4j 1.2
            args.add("-Dlog4j.configurationFile=" + log4jConfig); // log4j 2
            args.add("-Dlogback.configurationFile=" + log4jConfig);// logback

            final int ringBufferSize = getUserSpecifiedRingBufferSize();
            if (ringBufferSize >= 128) {
                args.add("-DAsyncLoggerConfig.RingBufferSize=" + ringBufferSize);
                args.add("-DAsyncLogger.RingBufferSize=" + ringBufferSize);
            }
            args.add("-DAsyncLoggerConfig.WaitStrategy=" + wait);
            args.add("-DAsyncLogger.WaitStrategy=" + wait);
            if (systemProperties != null) {
                Collections.addAll(args, systemProperties);
            }
            args.add("-cp");
            args.add(System.getProperty("java.class.path"));
            args.add(klass.getName());
            args.add(runner.implementationClass.getName());
            args.add(name);
            args.add(temp.getAbsolutePath());
            args.add(String.valueOf(threadCount));
            return args;
        }
