    @Test
    public void testProgressedSizeWithoutSize()
    {
         FileSizeFormat format = new FileSizeFormat( Locale.ENGLISH );

         long _0_bytes = 0L;
         assertEquals( "0 B", format.formatProgress( _0_bytes, -1L ) );

         long _1000_bytes = 1000L;
         assertEquals( "1.0 kB", format.formatProgress( _1000_bytes, -1L ) );

         long _1000_kilobytes = 1000L * 1000L;
         assertEquals( "1.0 MB", format.formatProgress( _1000_kilobytes, -1L ) );

         long _1000_megabytes = 1000L * 1000L * 1000L;
         assertEquals( "1.0 GB", format.formatProgress( _1000_megabytes, -1L ) );

    }
