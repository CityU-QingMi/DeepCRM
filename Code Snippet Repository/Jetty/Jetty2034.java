    private String stacktraces(final ThreadInfo[] threads, final int i)
    {
        if (i >= threads.length)
        {
            return "";
        }
        final ThreadInfo thread = threads[i];

        final StringBuilder trace = new StringBuilder();
        for (int stack_i = 0; stack_i < Math.min(thread.getStackTrace().length,MAX_STACK); stack_i++)
        {
            if (stack_i == (MAX_STACK - 1))
            {
                trace.append("    ...");
            }
            else
            {
                trace.append("    at ").append(thread.getStackTrace()[stack_i]).append("\n");
            }
        }

        return "\"" + thread.getThreadName() + "\", id " + thread.getThreadId() + " is " + thread.getThreadState() + " on " + thread.getLockName()
                + ", owned by " + thread.getLockOwnerName() + ", id " + thread.getLockOwnerId() + "\n" + trace + "\n\n" + stacktraces(threads,i + 1);
    }
