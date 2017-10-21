    @Override
    public void assertSession(String id, boolean exists)
    {
        try
        {
            boolean inDb = JdbcTestHelper.existsInSessionTable(id, false);
            if (exists)
                assertTrue(inDb);
            else
                assertFalse(inDb);
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }
    }
