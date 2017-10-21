    @Test
    public void testIsCertSign_NonStandard_Short()
    {
        X509Certificate bogusX509 = new X509CertificateAdapter()
        {
            @Override
            public boolean[] getKeyUsage()
            {
                boolean[] keyUsage = new boolean[6]; // at threshold
                keyUsage[5] = true;
                return keyUsage;
            }
        };
        
        assertThat("NonStandard X509", X509.isCertSign(bogusX509), is(true));
    }
