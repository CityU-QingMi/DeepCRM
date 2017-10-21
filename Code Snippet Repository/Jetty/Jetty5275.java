    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        out.append(String.valueOf(this)).append(" trustAll=").append(Boolean.toString(_trustAll)).append(System.lineSeparator());
    
        try
        {
/**/
/**/
/**/
/**/
            SSLEngine sslEngine = SSLContext.getDefault().createSSLEngine();
    
            List<Object> selections = new ArrayList<>();
            
            // protocols
            selections.add(new SslSelectionDump("Protocol",
                    sslEngine.getSupportedProtocols(),
                    sslEngine.getEnabledProtocols(),
                    getExcludeProtocols(),
                    getIncludeProtocols()));
            
            // ciphers
            selections.add(new SslSelectionDump("Cipher Suite",
                    sslEngine.getSupportedCipherSuites(),
                    sslEngine.getEnabledCipherSuites(),
                    getExcludeCipherSuites(),
                    getIncludeCipherSuites()));
            
            ContainerLifeCycle.dump(out, indent, selections);
        }
        catch (NoSuchAlgorithmException ignore)
        {
            LOG.ignore(ignore);
        }
    }
