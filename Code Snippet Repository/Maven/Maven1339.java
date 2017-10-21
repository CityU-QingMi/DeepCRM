    @Override
    public synchronized void transferProgressed( TransferEvent event )
        throws TransferCancelledException
    {
        TransferResource resource = event.getResource();
        transfers.put( resource, event.getTransferredBytes() );

        StringBuilder buffer = new StringBuilder( 128 );
        buffer.append( "Progress (" ).append(  transfers.size() ).append( "): " );

        synchronized ( transfers )
        {
            Iterator<Map.Entry<TransferResource, Long>> entries = transfers.entrySet().iterator();
            while ( entries.hasNext() )
            {
                Map.Entry<TransferResource, Long> entry = entries.next();
                long total = entry.getKey().getContentLength();
                Long complete = entry.getValue();
                buffer.append( getStatus( entry.getKey().getResourceName(), complete, total ) );
                if ( entries.hasNext() )
                {
                    buffer.append( " | " );
                }
            }
        }

        int pad = lastLength - buffer.length();
        lastLength = buffer.length();
        pad( buffer, pad );
        buffer.append( '\r' );
        out.print( buffer );
        out.flush();
    }
