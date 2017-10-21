    @Test
    public void testIsCertSign_Normal_Empty()
    {
        X509Certificate bogusX509 = new X509CertificateAdapter()
        {
            @Override
            public boolean[] getKeyUsage()
            {
                return new boolean[0];
            }
        };
        
        assertThat("Normal X509", X509.isCertSign(bogusX509), is(false));
    }
