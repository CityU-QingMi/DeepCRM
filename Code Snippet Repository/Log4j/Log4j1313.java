    @Override
    public Map<ThreadInformation, StackTraceElement[]> createThreadInfo() {
        final ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        final ThreadInfo[] array = bean.dumpAllThreads(true, true);

        final Map<ThreadInformation, StackTraceElement[]>  threads =
            new HashMap<>(array.length);
        for (final ThreadInfo info : array) {
            threads.put(new ExtendedThreadInformation(info), info.getStackTrace());
        }
        return threads;
    }
