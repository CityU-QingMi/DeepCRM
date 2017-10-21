    protected void verify(String host, SSLSession session) throws Exception {

        X509Certificate[] chain;
        X509Certificate   certificate;
        Principal         principal;
        PublicKey         publicKey;
        String            DN;
        String            CN;
        int               start;
        int               end;
        String            emsg;

        chain       = session.getPeerCertificateChain();
        certificate = chain[0];
        principal   = certificate.getSubjectDN();
        DN          = String.valueOf(principal);
        start       = DN.indexOf("CN=");

        if (start < 0) {
            throw new UnknownHostException(
                Error.getMessage(ErrorCode.M_SERVER_SECURE_VERIFY_1));
        }

        start += 3;
        end   = DN.indexOf(',', start);
        CN    = DN.substring(start, (end > -1) ? end
                                               : DN.length());

        if (CN.length() < 1) {
            throw new UnknownHostException(
                Error.getMessage(ErrorCode.M_SERVER_SECURE_VERIFY_2));
        }

        if (!CN.equalsIgnoreCase(host)) {

            // TLS_HOSTNAME_MISMATCH
            throw new UnknownHostException(
                Error.getMessage(
                    ErrorCode.M_SERVER_SECURE_VERIFY_3, 0, new Object[] {
                CN, host
            }));
        }
    }
