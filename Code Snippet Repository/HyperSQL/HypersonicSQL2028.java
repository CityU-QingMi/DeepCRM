    public synchronized void restart() throws IllegalStateException {

        if (this.isShutdown) {
            throw new IllegalStateException("isShutdown==true");
        } else if (this.taskRunnerThread == null) {
            this.taskRunnerThread =
                this.threadFactory.newThread(this.taskRunner);

            this.taskRunnerThread.start();
        } else {
            this.taskQueue.unpark();
        }
    }
