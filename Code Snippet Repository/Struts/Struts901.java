    public void append( char b )
        throws IOException
    {
        makeSpace( 1 );

        // couldn't make space
        if( limit >0 && end >= limit ) {
            flushBuffer();
        }
        buff[end++]=b;
    }
