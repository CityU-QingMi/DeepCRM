    @Test
    public void testBadMinMax() throws Exception
    {
        String[] tests = new String[]
        {
            "10.0.0.0-9.0.0.0",
            "9.0.0.0-[::10.0.0.0]",
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
