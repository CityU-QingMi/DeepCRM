    public static boolean updateMin(AtomicLong currentMin, long newValue)
    {
        long oldValue = currentMin.get();
        while (newValue < oldValue)
        {
            if (currentMin.compareAndSet(oldValue, newValue))
                return true;
            oldValue = currentMin.get();
        }
        return false;
    }
