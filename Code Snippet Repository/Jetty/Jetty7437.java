    @Test
    public void test14_16_PartialRange() throws Exception
    {
        String alpha = ALPHA;

        // server should not return a 416 if at least one syntactically valid ranges
        // are is satisfiable

        assertPartialContentRange("bytes=5-8,50-60","5-8/27",alpha.substring(5,8 + 1));
        assertPartialContentRange("bytes=50-60,5-8","5-8/27",alpha.substring(5,8 + 1));
    }
