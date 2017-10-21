    @Override
    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket)
    {
        if (_alias==null)
            return _delegate.chooseClientAlias(keyType,issuers,socket);

        for (String kt : keyType)
        {
            String[] aliases = _delegate.getClientAliases(kt,issuers);
            if (aliases!=null)
            {
                for (String a:aliases)
                    if (_alias.equals(a))
                        return _alias;
            }
        }

        return null;
    }
