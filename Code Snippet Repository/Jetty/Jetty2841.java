    protected boolean fillAndParseForContent()
    {
        boolean handled=false;
        while (_parser.inContentState())
        {
            int filled = fillRequestBuffer();
            handled = parseRequestBuffer();
            if (handled || filled<=0 || _input.hasContent())
                break;
        }
        return handled;
    }
