    static <T> void spinAssertEquals(T expected, Supplier<T> actualSupplier, long waitFor, TimeUnit units)
    {
        long now = System.nanoTime();
        long end = now + units.toNanos(waitFor);
        T actual = null;
        while (now < end)
        {
            actual = actualSupplier.get();
            if (actual == null && expected == null ||
                    actual != null && actual.equals(expected))
                break;
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                // Ignored
            }
            now = System.nanoTime();
        }

        Assert.assertEquals(expected, actual);
    }
