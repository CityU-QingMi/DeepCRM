    public static void invokeNonBlocking(Runnable task)
    {
        // a Choice exists, so we must indicate NonBlocking
        Boolean was_non_blocking = __nonBlocking.get();
        try
        {
            __nonBlocking.set(Boolean.TRUE);
            task.run();
        }
        finally
        {
            __nonBlocking.set(was_non_blocking);
        }
    }
