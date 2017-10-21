    @Test
    public void testLocked()
    {
        Locker lock = new Locker();
        assertFalse(lock.isLocked());

        try(Locker.Lock l = lock.lock())
        {
            assertTrue(lock.isLocked());
        }
        finally
        {
            assertFalse(lock.isLocked());
        }

        assertFalse(lock.isLocked());
    }
