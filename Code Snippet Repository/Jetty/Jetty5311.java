    public static Runnable asPreferred(Runnable task, InvocationType preferredInvocationType)
    {
        switch (getInvocationType(task))
        {
            case BLOCKING:
            case NON_BLOCKING:
                break;

            case EITHER:
                if (preferredInvocationType == InvocationType.NON_BLOCKING)
                    return () -> invokeNonBlocking(task);
                break;
        }

        return task;
    }
