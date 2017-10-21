    public synchronized void shutdownImmediately() {

        if (!this.isShutdown) {
            final Thread runner = this.taskRunnerThread;

            this.isShutdown = true;

            if (runner != null && runner.isAlive()) {
                runner.interrupt();
            }

            this.taskQueue.cancelAllTasks();
        }
    }
