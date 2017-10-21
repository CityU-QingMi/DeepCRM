    @Parameters(name = "")
    public static List<Object[]> data()
    {
        List<Object[]> ret = new ArrayList<Object[]>();
        
        Class<?> gzipFilters[] = new Class<?>[] { GzipFilter.class, AsyncGzipFilter.class };
        Class<?> contentServlets[] = new Class<?>[] { 
                TestServletLengthStreamTypeWrite.class, 
                AsyncTimeoutDispatchWrite.Default.class,
                AsyncTimeoutDispatchWrite.Passed.class,
                AsyncTimeoutCompleteWrite.Default.class,
                AsyncTimeoutCompleteWrite.Passed.class,
                AsyncScheduledDispatchWrite.Default.class,
                AsyncScheduledDispatchWrite.Passed.class,
                };

        for (Class<?> contentServlet: contentServlets)
        {
            for (Class<?> gzipFilter : gzipFilters)
            {
                ret.add(new Object[] { 0, "empty.txt", !EXPECT_COMPRESSED, gzipFilter, contentServlet });
                ret.add(new Object[] { TINY, "file-tiny.txt", !EXPECT_COMPRESSED, gzipFilter, contentServlet });
                ret.add(new Object[] { SMALL, "file-small.txt", EXPECT_COMPRESSED, gzipFilter, contentServlet });
                ret.add(new Object[] { LARGE, "file-large.txt", EXPECT_COMPRESSED, gzipFilter, contentServlet });
                ret.add(new Object[] { LARGE, "file-large.mp3", !EXPECT_COMPRESSED, gzipFilter, contentServlet });
            }
        }

        return ret;
    }
