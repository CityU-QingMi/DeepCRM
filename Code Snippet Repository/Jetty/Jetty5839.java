    @Test
    public void testIsCertSign_Normal_NoSupported()
    {
        X509Certificate bogusX509 = new X509CertificateAdapter()
        {
            @Override
            public boolean[] getKeyUsage()
            {
                boolean[] keyUsage = new boolean[8];
                keyUsage[5] = false;
                return keyUsage;
            }
        };
        
        assertThat("Normal X509", X509.isCertSign(bogusX509), is(false));
    }
