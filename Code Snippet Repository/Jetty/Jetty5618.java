    @Test
    public void testBlockerClose() throws Exception
    {
        try (Blocker blocker=sbcb.acquire())
        {
            SharedBlockingCallback.LOG.info("Blocker not complete "+blocker+" warning is expected...");
        }
        
        Assert.assertEquals(1,notComplete.get());
    }
