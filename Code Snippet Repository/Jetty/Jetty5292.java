        @Override
        public boolean matches(SNIServerName serverName)
        {
            if (LOG.isDebugEnabled())
                LOG.debug("SNI matching for {}", serverName);

            if (serverName instanceof SNIHostName)
            {
                String host = _host = ((SNIHostName)serverName).getAsciiName();
                host = StringUtil.asciiToLowerCase(host);

                // Try an exact match
                _x509 = _certHosts.get(host);

                // Else try an exact wild match
                if (_x509 == null)
                {
                    _x509 = _certWilds.get(host);

                    // Else try an 1 deep wild match
                    if (_x509 == null)
                    {
                        int dot = host.indexOf('.');
                        if (dot >= 0)
                        {
                            String domain = host.substring(dot + 1);
                            _x509 = _certWilds.get(domain);
                        }
                    }
                }

                if (LOG.isDebugEnabled())
                    LOG.debug("SNI matched {}->{}", host, _x509);
            }
            else
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("SNI no match for {}", serverName);
            }

            // Return true and allow the KeyManager to accept or reject when choosing a certificate.
            // If we don't have a SNI host, or didn't see any certificate aliases,
            // just say true as it will either somehow work or fail elsewhere.
            return true;
        }
