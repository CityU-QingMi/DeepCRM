    @Override
    protected void renderThrowable( Throwable t, PrintStream stream )
    {
        stream.print( buffer().failure( t.getClass().getName() ) );
        if ( t.getMessage() != null )
        {
            stream.print( ": " );
            stream.print( buffer().failure( t.getMessage() ) );
        }
        stream.println();

        while ( t != null )
        {
            for ( StackTraceElement e : t.getStackTrace() )
            {
                stream.print( "    " );
                stream.print( buffer().strong( "at" ) );
                stream.print( " " + e.getClassName() + "." + e.getMethodName() );
                stream.print( buffer().a( " (" ).strong( getLocation( e ) ).a( ")" ) );
                stream.println();
            }

            t = t.getCause();
            if ( t != null )
            {
                stream.print( buffer().strong( "Caused by" ).a( ": " ).a( t.getClass().getName() ) );
                if ( t.getMessage() != null )
                {
                    stream.print( ": " );
                    stream.print( buffer().failure( t.getMessage() ) );
                }
                stream.println();
            }
        }
    }
