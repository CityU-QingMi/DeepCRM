    @Test
    public void testBadLegacy() throws Exception
    {
        String[] tests = new String[]
        {
            "9.0-10.0",
            "10.0.0--1.1",
            "10.0.0-256.1",
        };

        InetAddressSet set = new InetAddressSet();
        
        for (String t:tests)
        {
            try
            {
                set.add(t);
                fail(t);
            }
            catch(IllegalArgumentException e)
            {
                Assert.assertThat(e.getMessage(),Matchers.containsString(t));
            }
        }
    }
