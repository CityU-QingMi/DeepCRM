    public void execute(Runnable command) throws RuntimeException {

        if (!isShutdown) {
            synchronized (queue) {
                queue.addLast(command);
            }

            restart();
        }
    }
