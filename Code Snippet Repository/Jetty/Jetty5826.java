    @Before
    public void setUp() throws Exception
    {
        cf = new SslContextFactory();
        
        java.security.cert.CertPathBuilder certPathBuilder = java.security.cert.CertPathBuilder.getInstance("PKIX");
        java.security.cert.PKIXRevocationChecker revocationChecker = (java.security.cert.PKIXRevocationChecker) certPathBuilder.getRevocationChecker();
        revocationChecker.setOptions(java.util.EnumSet.of(
            java.security.cert.PKIXRevocationChecker.Option.valueOf("PREFER_CRLS"),
            java.security.cert.PKIXRevocationChecker.Option.valueOf("SOFT_FAIL"),
            java.security.cert.PKIXRevocationChecker.Option.valueOf("NO_FALLBACK")));
        cf.setPkixCertPathChecker(revocationChecker);
        
    }
