    public Object getValue( String expression )
    {
        if ( "build.timestamp".equals( expression ) || "maven.build.timestamp".equals( expression ) )
        {
            if ( formattedDate == null && startTime != null )
            {
                formattedDate = new SimpleDateFormat( format ).format( startTime );
            }

            return formattedDate;
        }

        return null;
    }
