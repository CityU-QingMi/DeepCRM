    protected Holder(Source source)
    {
        super(source);
        switch(_source.getOrigin())
        {
            case JAVAX_API:
            case DESCRIPTOR:
            case ANNOTATION:
                _asyncSupported=false;
                break;
            default:
                _asyncSupported=true;
        }
    }
