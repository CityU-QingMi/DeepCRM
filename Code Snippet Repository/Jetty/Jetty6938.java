    public int getErrorCount(Class<? extends Throwable> errorType)
    {
        int count = 0;
        for (Throwable error : errors)
        {
            if (errorType.isInstance(error))
            {
                count++;
            }
        }
        return count;
    }
