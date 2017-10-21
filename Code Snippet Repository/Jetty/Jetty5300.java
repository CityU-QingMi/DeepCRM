    public X509(String alias,X509Certificate x509) throws CertificateParsingException, InvalidNameException
    {
        _alias=alias;
        _x509 = x509;

        // Look for alternative name extensions
        boolean named=false;
        Collection<List<?>> altNames = x509.getSubjectAlternativeNames();
        if (altNames!=null)
        {
            for (List<?> list : altNames)
            {
                if (((Number)list.get(0)).intValue() == SUBJECT_ALTERNATIVE_NAMES__DNS_NAME)
                {
                    String cn = list.get(1).toString();
                    if (LOG.isDebugEnabled())
                        LOG.debug("Certificate SAN alias={} CN={} in {}",alias,cn,this);
                    if (cn!=null)
                    {
                        named=true;
                        addName(cn);
                    }
                }
            }
        }

        // If no names found, look up the CN from the subject
        if (!named)
        {
            LdapName name=new LdapName(x509.getSubjectX500Principal().getName(X500Principal.RFC2253));
            for (Rdn rdn : name.getRdns())
            {
                if (rdn.getType().equalsIgnoreCase("CN"))
                {
                    String cn = rdn.getValue().toString();
                    if (LOG.isDebugEnabled())
                        LOG.debug("Certificate CN alias={} CN={} in {}",alias,cn,this);
                    if (cn!=null && cn.contains(".") && !cn.contains(" "))
                        addName(cn);
                }
            }
        }
    }
