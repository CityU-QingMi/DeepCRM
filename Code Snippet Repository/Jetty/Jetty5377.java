    @Test
    @Slow
    public void testDateCache() throws Exception
    {
        //@WAS: Test t = new Test("org.eclipse.jetty.util.DateCache");
        //                            012345678901234567890123456789
        DateCache dc = new DateCache("EEE, dd MMM yyyy HH:mm:ss zzz ZZZ",Locale.US,TimeZone.getTimeZone("GMT"));

        Thread.sleep(2000);

        long now=System.currentTimeMillis();
        long end=now+3000;
        String f=dc.formatNow(now);
        String last=f;

        int hits=0;
        int misses=0;

        while (now<end)
        {
            last=f;
            f=dc.formatNow(now);
            // System.err.printf("%s %s%n",f,last==f);
            if (last==f)
                hits++;
            else
                misses++;

            TimeUnit.MILLISECONDS.sleep(100);
            now=System.currentTimeMillis();
        }
        Assert.assertThat(hits,Matchers.greaterThan(misses));
    }
