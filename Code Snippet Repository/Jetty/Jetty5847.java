    @Test
    public void testLockedException()
    {
        Locker lock = new Locker();
        assertFalse(lock.isLocked());

        try(Locker.Lock l = lock.lock())
        {
            assertTrue(lock.isLocked());
            throw new Exception();
        }
        catch(Exception e)
        {
            assertFalse(lock.isLocked());
        }
        finally
        {
            assertFalse(lock.isLocked());
        }

        assertFalse(lock.isLocked());
    }
