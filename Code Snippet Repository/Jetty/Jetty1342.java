    @Override
    public String toString()
    {
        if (_uri==null)
        {
            StringBuilder out = new StringBuilder();
            
            if (_scheme!=null)
                out.append(_scheme).append(':');
            
            if (_host != null)
            {
                out.append("//");
                if (_user != null)
                    out.append(_user).append('@');
                out.append(_host);
            }
            
            if (_port>0)
                out.append(':').append(_port);
            
            if (_path!=null)
                out.append(_path);
            
            if (_query!=null)
                out.append('?').append(_query);
            
            if (_fragment!=null)
                out.append('#').append(_fragment);
            
            if (out.length()>0)
                _uri=out.toString();
            else
                _uri="";
        }
        return _uri;
    }
