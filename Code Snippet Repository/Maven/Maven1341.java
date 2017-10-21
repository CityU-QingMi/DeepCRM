    private void overridePreviousTransfer( TransferEvent event )
    {
        if ( lastLength > 0 )
        {
            StringBuilder buffer = new StringBuilder( 128 );
            pad( buffer, lastLength );
            buffer.append( '\r' );
            out.print( buffer );
            out.flush();
            lastLength = 0;
        }
    }
