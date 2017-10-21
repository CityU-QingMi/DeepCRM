    public void setEventType( final int eventType )
    {
        switch ( eventType )
        {
            case TRANSFER_INITIATED:
                break;
            case TRANSFER_STARTED:
                break;
            case TRANSFER_COMPLETED:
                break;
            case TRANSFER_PROGRESS:
                break;
            case TRANSFER_ERROR:
                break;
            default :
                throw new IllegalArgumentException( "Illegal event type: " + eventType );
        }

        this.eventType = eventType;
    }
