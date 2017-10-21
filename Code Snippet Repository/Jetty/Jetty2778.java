    public void addCookieField(String f)
    {
        if (f==null)
            return;
        f=f.trim();
        if (f.length()==0)
            return;
            
        if (_fieldList.size()>_fields)
        {
            if (f.equals(_fieldList.get(_fields)))
            {
                _fields++;
                return;
            }
            
            while (_fieldList.size()>_fields)
                _fieldList.remove(_fields);
        }
        _cookies=null;
        _lastCookies=null;
        _fieldList.add(_fields++,f);
    }
