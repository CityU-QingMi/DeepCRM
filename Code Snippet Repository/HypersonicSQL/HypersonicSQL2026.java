    public synchronized void shutdownImmediately() {

        isShutdown = true;

        if (taskRunnerThread != null) {
            taskRunnerThread.interrupt();
        }

        synchronized (queue) {
            queue.clear();
            queue.addLast(SHUTDOWNTASK);
        }
    }
