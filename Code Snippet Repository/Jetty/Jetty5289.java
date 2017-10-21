    public SSLParameters customize(SSLParameters sslParams)
    {
        sslParams.setEndpointIdentificationAlgorithm(getEndpointIdentificationAlgorithm());
        sslParams.setUseCipherSuitesOrder(isUseCipherSuitesOrder());
        if (!_certHosts.isEmpty() || !_certWilds.isEmpty())
            sslParams.setSNIMatchers(Collections.singletonList(new AliasSNIMatcher()));
        if (_selectedCipherSuites != null)
            sslParams.setCipherSuites(_selectedCipherSuites);
        if (_selectedProtocols != null)
            sslParams.setProtocols(_selectedProtocols);
        if (getWantClientAuth())
            sslParams.setWantClientAuth(true);
        if (getNeedClientAuth())
            sslParams.setNeedClientAuth(true);
        return sslParams;
    }
