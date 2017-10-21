    public void setRequestType( final int requestType )
    {
        switch ( requestType )
        {
            case REQUEST_PUT:
                break;
            case REQUEST_GET:
                break;
            default :
                throw new IllegalArgumentException( "Illegal request type: " + requestType );
        }

        this.requestType = requestType;
    }
