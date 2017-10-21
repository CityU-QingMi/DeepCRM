    @Test
    public void test14_35_PartialRange() throws Exception
    {
        //
        // test various valid range specs that have not been
        // tested yet
        //

        String alpha = ALPHA;

        // server should not return a 416 if at least one syntactically valid ranges
        // are is satisfiable

        assertByteRange("bytes=5-8,50-60","5-8/27",alpha.substring(5,8 + 1));
        assertByteRange("bytes=50-60,5-8","5-8/27",alpha.substring(5,8 + 1));
    }
