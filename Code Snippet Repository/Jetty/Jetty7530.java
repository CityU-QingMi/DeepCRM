    @Override
    public void assertSession(String id, boolean exists)
    {
        try
        {
            Set<String> ids = GCloudTestSuite.__testSupport.getSessionIds();
            if (exists)
                assertTrue(ids.contains(id));
            else
                assertFalse(ids.contains(id));
        }
        catch (Exception e)
        {
            fail(e.getMessage());
        }

    }
