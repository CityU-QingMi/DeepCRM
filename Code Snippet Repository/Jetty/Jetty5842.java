    @Test
    public void testIsCertSign_Normal_Null()
    {
        X509Certificate bogusX509 = new X509CertificateAdapter()
        {
            @Override
            public boolean[] getKeyUsage()
            {
                return null;
            }
        };
        
        assertThat("Normal X509", X509.isCertSign(bogusX509), is(false));
    }
