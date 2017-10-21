    public BackgroundProcess(String threadName, final ActionInvocation invocation, int threadPriority) {
        this.invocation = invocation;
        this.action = invocation.getAction();
        try {
            final Thread t = new Thread(new Runnable() {
                public void run() {
                    try {
                        beforeInvocation();
                        result = invocation.invokeActionOnly();
                        afterInvocation();
                    } catch (Exception e) {
                        exception = e;
                    }

                    done = true;
                }
            });
            t.setName(threadName);
            t.setPriority(threadPriority);
            t.start();
        } catch (Exception e) {
            exception = e;
        }
    }
