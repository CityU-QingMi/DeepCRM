    public static boolean updateMax(AtomicInteger currentMax, int newValue)
    {
        int oldValue = currentMax.get();
        while (newValue > oldValue)
        {
            if (currentMax.compareAndSet(oldValue, newValue))
                return true;
            oldValue = currentMax.get();
        }
        return false;
    }
