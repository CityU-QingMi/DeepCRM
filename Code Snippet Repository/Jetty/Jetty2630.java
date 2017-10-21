    @Override
    public void dump(Appendable out,String indent) throws IOException
    {
        // TODO these should all be beans
        dumpBeans(out,indent,
                Collections.singleton(getLoginService()),
                Collections.singleton(getIdentityService()),
                Collections.singleton(getAuthenticator()),
                Collections.singleton(_roles),
                _constraintMap.entrySet());
    }
