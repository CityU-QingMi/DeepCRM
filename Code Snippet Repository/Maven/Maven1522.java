    public void add( ModelProblemCollectorRequest req )
    {
        switch ( req.getSeverity() )
        {
            case FATAL:
                fatals.add( req.getMessage() );
                break;
            case ERROR:
                errors.add( req.getMessage() );
                break;
            case WARNING:
                warnings.add( req.getMessage() );
                break;
        }

    }
