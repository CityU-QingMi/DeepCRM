    private synchronized void sampleThreads()
    {
        if ((lastSampled + 50L) < System.currentTimeMillis())
        {
            lastSampled = System.currentTimeMillis();
            for (final Thread.State state : Thread.State.values())
            {
                states.put(state,0);
            }

            for (final ThreadInfo thread : threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds()))
            {
                if (thread != null)
                {
                    final Thread.State state = thread.getThreadState();
                    states.put(state,states.get(state) + 1);
                }
                else
                {
                    states.put(Thread.State.TERMINATED,states.get(Thread.State.TERMINATED) + 1);
                }
            }
        }
    }
