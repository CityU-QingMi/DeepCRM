    public static boolean updateMax(AtomicLong currentMax, long newValue)
    {
        long oldValue = currentMax.get();
        while (newValue > oldValue)
        {
            if (currentMax.compareAndSet(oldValue, newValue))
                return true;
            oldValue = currentMax.get();
        }
        return false;
    }
