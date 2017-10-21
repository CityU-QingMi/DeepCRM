    public Servlet get(final String location) throws InterruptedException {
        while (true) {
            Future<Servlet> future = cache.get(location);
            if (future == null) {
                Callable<Servlet> loadJSPCallable = new Callable<Servlet>() {
                    public Servlet call() throws Exception {
                        return jspLoader.load(location);
                    }
                };
                FutureTask<Servlet> futureTask = new FutureTask<Servlet>(loadJSPCallable);
                future = cache.putIfAbsent(location, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(location, future);
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }
    }
