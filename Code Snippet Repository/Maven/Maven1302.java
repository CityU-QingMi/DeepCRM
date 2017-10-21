    public static String formatDuration( long duration )
    {
        // CHECKSTYLE_OFF: MagicNumber
        long ms = duration % 1000;
        long s = ( duration / ONE_SECOND ) % 60;
        long m = ( duration / ONE_MINUTE ) % 60;
        long h = ( duration / ONE_HOUR ) % 24;
        long d = duration / ONE_DAY;
        // CHECKSTYLE_ON: MagicNumber

        String format;
        if ( d > 0 )
        {
            // Length 11+ chars
            format = "%d d %02d:%02d h";
        }
        else if ( h > 0 )
        {
            // Length 7 chars
            format = "%2$02d:%3$02d h";
        }
        else if ( m > 0 )
        {
            // Length 9 chars
            format = "%3$02d:%4$02d min";
        }
        else
        {
            // Length 7-8 chars
            format = "%4$d.%5$03d s";
        }

        return String.format( format, d, h, m, s, ms );
    }
