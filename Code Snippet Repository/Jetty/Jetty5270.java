    @Override
    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine)
    {
        if (_alias==null)
            return _delegate.chooseEngineServerAlias(keyType,issuers,engine);

        String[] aliases = _delegate.getServerAliases(keyType,issuers);
        if (aliases!=null)
        {
            for (String a:aliases)
                if (_alias.equals(a))
                    return _alias;
        }

        return null;
    }
