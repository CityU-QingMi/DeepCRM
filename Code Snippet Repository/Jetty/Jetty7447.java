    @Test
    public void test14_35_Range() throws Exception
    {
        //
        // test various valid range specs that have not been
        // tested yet
        //

        String alpha = ALPHA;

        // First 3 bytes
        assertByteRange("bytes=0-2","0-2/27",alpha.substring(0,2 + 1));

        // From byte offset 23 thru the end of the content
        assertByteRange("bytes=23-","23-26/27",alpha.substring(23));

        // Request byte offset 23 thru 42 (only 26 bytes in content)
        // The last 3 bytes are returned.
        assertByteRange("bytes=23-42","23-26/27",alpha.substring(23,26 + 1));

        // Request the last 3 bytes
        assertByteRange("bytes=-3","24-26/27",alpha.substring(24,26 + 1));
    }
