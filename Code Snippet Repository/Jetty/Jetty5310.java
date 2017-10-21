    public static void invokePreferred(Runnable task, InvocationType preferredInvocationType)
    {
        switch (getInvocationType(task))
        {
            case BLOCKING:
            case NON_BLOCKING:
                task.run();
                break;

            case EITHER:
                if (getInvocationType(task) == InvocationType.EITHER && preferredInvocationType == InvocationType.NON_BLOCKING)
                    invokeNonBlocking(task);
                else
                    task.run();
                break;
        }
    }
