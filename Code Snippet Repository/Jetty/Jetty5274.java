    @Override
    public String chooseEngineServerAlias(String keyType, Principal[] issuers, SSLEngine engine)
    {
        String alias = chooseServerAlias(keyType,issuers,engine.getSSLParameters().getSNIMatchers(),engine.getHandshakeSession());
        if (alias==NO_MATCHERS)
            alias=_delegate.chooseEngineServerAlias(keyType,issuers,engine);
        if (LOG.isDebugEnabled())
            LOG.debug("Chose alias {}/{} on {}",alias,keyType,engine);
        return alias;
    }
