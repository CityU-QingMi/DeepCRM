    protected boolean isAddrUriAllowed(String addr, String path)
    {
        if (_white.size()>0)
        {
            boolean match = false;
            boolean matchedByPath = false;

            for (Map.Entry<String,IPAddressMap<Boolean>> entry : _white.getMatches(path))
            {
                matchedByPath=true;
                IPAddressMap<Boolean> addrMap = entry.getValue();
                if ((addrMap!=null && (addrMap.size()==0 || addrMap.match(addr)!=null)))
                {
                    match=true;
                    break;
                }
            }
            
            if (_whiteListByPath)
            {
                if (matchedByPath && !match)
                    return false;
            }
            else
            {
                if (!match)
                    return false;
            }
        }

        if (_black.size() > 0)
        {
            for (Map.Entry<String,IPAddressMap<Boolean>> entry : _black.getMatches(path))
            {
                IPAddressMap<Boolean> addrMap = entry.getValue();
                if (addrMap!=null && (addrMap.size()==0 || addrMap.match(addr)!=null))
                    return false;
            }
            
        }

        return true;
    }
