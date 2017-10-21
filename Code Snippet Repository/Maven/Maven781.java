    private String toString( Object obj )
    {
        String str;
        if ( obj != null && obj.getClass().isArray() )
        {
            int n = Array.getLength( obj );
            StringBuilder buf = new StringBuilder( 256 );
            buf.append( '[' );
            for ( int i = 0; i < n; i++ )
            {
                if ( i > 0 )
                {
                    buf.append( ", " );
                }
                buf.append( String.valueOf( Array.get( obj, i ) ) );
            }
            buf.append( ']' );
            str = buf.toString();
        }
        else
        {
            str = String.valueOf( obj );
        }
        return str;
    }
