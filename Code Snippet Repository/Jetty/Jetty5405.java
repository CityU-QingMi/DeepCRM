    @Test
    public void testBadCIDR() throws Exception
    {
        String[] tests = new String[]
        {
            "unknown/8",
            "1.2.3.4/-1",
            "1.2.3.4/xxx",
            "1.2.3.4/33",
            "255.255.8.0/16",
            "255.255.8.1/17",
            "[::1]/129",
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
