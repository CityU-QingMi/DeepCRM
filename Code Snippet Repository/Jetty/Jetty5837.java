    @Test
    public void testNoKeyConfig() throws Exception
    {
        try (StacklessLogging stackless = new StacklessLogging(AbstractLifeCycle.class))
        {
            cf.setTrustStorePath("/foo");
            cf.start();
            Assert.fail();
        }
        catch (IllegalStateException e)
        {
            Assert.assertThat(e.toString(),Matchers.containsString("IllegalStateException: no valid keystore"));
        }
    }
