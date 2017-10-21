    @Test
    public void testBadSingleton() throws Exception
    {
        String[] tests = new String[]
        {
            "unknown",
            "1.2.3.4.5.6.7.8.9.10.11.12.13.14.15.16",
            "a.b.c.d",
            "[::1",
            "[xxx]",
            "[:::1]",
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
