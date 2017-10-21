    @Test
    public void testData()
        throws Exception
    {
        SampleStatistic stats = new SampleStatistic();
        for (int d=0;d<data.length;d++)
        {
            stats.reset();
            for (long x : data[d])
                stats.set(x);

            assertEquals("count"+d,data[d].length, (int)stats.getCount());
            assertNearEnough("mean"+d,results[d][0], stats.getMean());
            assertNearEnough("stddev"+d,results[d][1], stats.getStdDev());
        }
    }
