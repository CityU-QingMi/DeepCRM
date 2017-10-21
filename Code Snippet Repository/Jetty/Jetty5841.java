    @Test
    public void testIsCertSign_NonStandard_Shorter()
    {
        X509Certificate bogusX509 = new X509CertificateAdapter()
        {
            @Override
            public boolean[] getKeyUsage()
            {
                boolean[] keyUsage = new boolean[5]; // just below threshold
                return keyUsage;
            }
        };
        
        assertThat("NonStandard X509", X509.isCertSign(bogusX509), is(false));
    }
