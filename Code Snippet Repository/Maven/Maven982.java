    public String toString()
    {
        StringBuilder sb = new StringBuilder( 64 );

        sb.append( "TransferEvent[" );

        switch ( this.getRequestType() )
        {
            case REQUEST_GET:
                sb.append( "GET" );
                break;
            case REQUEST_PUT:
                sb.append( "PUT" );
                break;
            default:
                sb.append( this.getRequestType() );
                break;
        }

        sb.append( '|' );
        switch ( this.getEventType() )
        {
            case TRANSFER_COMPLETED:
                sb.append( "COMPLETED" );
                break;
            case TRANSFER_ERROR:
                sb.append( "ERROR" );
                break;
            case TRANSFER_INITIATED:
                sb.append( "INITIATED" );
                break;
            case TRANSFER_PROGRESS:
                sb.append( "PROGRESS" );
                break;
            case TRANSFER_STARTED:
                sb.append( "STARTED" );
                break;
            default:
                sb.append( this.getEventType() );
                break;
        }

        sb.append( '|' );
        sb.append( this.getLocalFile() ).append( '|' );
        sb.append( ']' );

        return sb.toString();
    }
