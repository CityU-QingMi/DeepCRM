    public SecureRequestCustomizer(
            @Name("sniHostCheck")boolean sniHostCheck,
            @Name("stsMaxAgeSeconds")long stsMaxAgeSeconds,
            @Name("stsIncludeSubdomains")boolean stsIncludeSubdomains)
    {
        _sniHostCheck=sniHostCheck;
        _stsMaxAge=stsMaxAgeSeconds;
        _stsIncludeSubDomains=stsIncludeSubdomains;
        formatSTS();
    }
