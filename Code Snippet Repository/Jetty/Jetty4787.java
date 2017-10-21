    public static boolean updateMin(AtomicInteger currentMin, int newValue)
    {
        int oldValue = currentMin.get();
        while (newValue < oldValue)
        {
            if (currentMin.compareAndSet(oldValue, newValue))
                return true;
            oldValue = currentMin.get();
        }
        return false;
    }
