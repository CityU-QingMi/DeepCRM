    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket)
    {
        if (_alias==null)
            return _delegate.chooseServerAlias(keyType,issuers,socket);

        String[] aliases = _delegate.getServerAliases(keyType,issuers);
        if (aliases!=null)
        {
            for (String a:aliases)
                if (_alias.equals(a))
                    return _alias;
        }

        return null;
    }
