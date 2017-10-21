    public void setForwardedOnly(boolean rfc7239only)
    {
        if (rfc7239only)
        {
            if (_forwardedHeader==null)
                _forwardedHeader=HttpHeader.FORWARDED.toString();
            _forwardedHostHeader=null;
            _forwardedHostHeader=null;
            _forwardedServerHeader=null;
            _forwardedForHeader=null;
            _forwardedProtoHeader=null;
            _forwardedHttpsHeader=null;
        }
        else
        {
            if (_forwardedHostHeader==null)
                _forwardedHostHeader = HttpHeader.X_FORWARDED_HOST.toString();
            if (_forwardedServerHeader==null)
                _forwardedServerHeader = HttpHeader.X_FORWARDED_SERVER.toString();
            if (_forwardedForHeader==null)
                _forwardedForHeader = HttpHeader.X_FORWARDED_FOR.toString();
            if (_forwardedProtoHeader==null)
                _forwardedProtoHeader = HttpHeader.X_FORWARDED_PROTO.toString();
            if (_forwardedHttpsHeader==null)
                _forwardedHttpsHeader = "X-Proxied-Https";
        }
    }
