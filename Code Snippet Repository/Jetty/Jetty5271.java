    @Override
    public String chooseEngineClientAlias(String keyType[], Principal[] issuers, SSLEngine engine)
    {
        if (_alias==null)
            return _delegate.chooseEngineClientAlias(keyType,issuers,engine);

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
