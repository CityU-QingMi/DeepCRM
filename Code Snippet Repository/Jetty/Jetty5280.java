    protected void processIncludeCipherSuites(String[] supportedCipherSuites, List<String> selected_ciphers)
    {
        for (String cipherSuite : _includeCipherSuites)
        {
            Pattern p = Pattern.compile(cipherSuite);
            boolean added = false;
            for (String supportedCipherSuite : supportedCipherSuites)
            {
                Matcher m = p.matcher(supportedCipherSuite);
                if (m.matches())
                {
                    added = true;
                    selected_ciphers.add(supportedCipherSuite);
                }

            }
            if (!added)
                LOG.info("No Cipher matching '{}' is supported", cipherSuite);
        }
    }
