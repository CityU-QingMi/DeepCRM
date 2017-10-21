    public static void invokePreferNonBlocking(Runnable task)
    {
        switch (getInvocationType(task))
        {
            case BLOCKING:
            case NON_BLOCKING:
                task.run();
                break;

            case EITHER:
                // a Choice exists, so we must indicate NonBlocking
                invokeNonBlocking(task);
                break;
        }
    }
