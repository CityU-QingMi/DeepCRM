    @Test
    public void testProgressedSizeWithSize()
    {
         FileSizeFormat format = new FileSizeFormat( Locale.ENGLISH );

         long _0_bytes = 0L;
         long _400_bytes = 400L;
         long _800_bytes = 2L * _400_bytes;
         assertEquals( "0/800 B", format.formatProgress( _0_bytes, _800_bytes ) );
         assertEquals( "400/800 B", format.formatProgress( _400_bytes, _800_bytes ) );
         assertEquals( "800 B", format.formatProgress( _800_bytes, _800_bytes ) );

         long _4000_bytes = 4000L;
         long _8000_bytes = 2L * _4000_bytes;
         long _50_kilobytes = 50000L;
         assertEquals( "0/8.0 kB", format.formatProgress( _0_bytes, _8000_bytes ) );
         assertEquals( "0.4/8.0 kB", format.formatProgress( _400_bytes, _8000_bytes ) );
         assertEquals( "4.0/8.0 kB", format.formatProgress( _4000_bytes, _8000_bytes ) );
         assertEquals( "8.0 kB", format.formatProgress( _8000_bytes, _8000_bytes ) );
         assertEquals( "8.0/50 kB", format.formatProgress( _8000_bytes, _50_kilobytes ) );
         assertEquals( "16/50 kB", format.formatProgress( 2L * _8000_bytes, _50_kilobytes ) );
         assertEquals( "50 kB", format.formatProgress( _50_kilobytes, _50_kilobytes ) );

         long _500_kilobytes = 500000L;
         long _1000_kilobytes = 2L * _500_kilobytes;;
         long _5000_kilobytes = 5L * _1000_kilobytes;
         long _15_megabytes = 3L * _5000_kilobytes;
         assertEquals( "0/5.0 MB", format.formatProgress( _0_bytes, _5000_kilobytes ) );
         assertEquals( "0.5/5.0 MB", format.formatProgress( _500_kilobytes, _5000_kilobytes ) );
         assertEquals( "1.0/5.0 MB", format.formatProgress( _1000_kilobytes, _5000_kilobytes ) );
         assertEquals( "5.0 MB", format.formatProgress( _5000_kilobytes, _5000_kilobytes ) );
         assertEquals( "5.0/15 MB", format.formatProgress( _5000_kilobytes, _15_megabytes ) );
         assertEquals( "15 MB", format.formatProgress( _15_megabytes, _15_megabytes ) );

         long _500_megabytes = 500000000L;
         long _1000_megabytes = 2L * _500_megabytes;
         long _5000_megabytes = 5L * _1000_megabytes;
         long _15_gigabytes = 3L * _5000_megabytes;
         assertEquals( "0/500 MB", format.formatProgress( _0_bytes, _500_megabytes ) );
         assertEquals( "1.0/5.0 GB", format.formatProgress( _1000_megabytes, _5000_megabytes ) );
         assertEquals( "5.0 GB", format.formatProgress( _5000_megabytes, _5000_megabytes ) );
         assertEquals( "5.0/15 GB", format.formatProgress( _5000_megabytes, _15_gigabytes ) );
         assertEquals( "15 GB", format.formatProgress( _15_gigabytes, _15_gigabytes ) );
    }
