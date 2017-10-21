    private ThreadInfo[] findDeadlock()
        throws IllegalAccessException, InvocationTargetException
    {
        final long[] threadIds = (long[])findDeadlockMethod.invoke(threadMXBean,(Object[])null);

        if (threadIds == null || threadIds.length < 1)
        {
            // no deadlock, we're done
            return null;
        }

        final ThreadInfo[] threads = threadMXBean.getThreadInfo(threadIds,Integer.MAX_VALUE);
        return threads;
    }
