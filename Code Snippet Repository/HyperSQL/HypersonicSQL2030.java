    protected Task addTask(final long first, final Runnable runnable,
                           final long period, boolean relative) {

        if (this.isShutdown) {
            throw new IllegalStateException("shutdown");
        }

        final Task task = new Task(first, runnable, period, relative);

        // sychronized
        this.taskQueue.addTask(task);

        // sychronized
        this.restart();

        return task;
    }
