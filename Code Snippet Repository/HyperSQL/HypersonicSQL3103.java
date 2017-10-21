    public Socket createSocket(String host, int port) throws Exception {

        SSLSocket socket;

        socket = (SSLSocket) getSocketFactoryImpl().createSocket(host, port);

        socket.addHandshakeCompletedListener(this);
        socket.startHandshake();

// unsaved@users
// For https protocol, the protocol handler should do this verification
// (Sun's implementation does), but if we do not use the Protocol
// handler (which is only available in Java >= 1.4), then we need to do
// the verification: hostname == cert CN
//
// campbell-burnet@users 20030503:
// CHEKME/TODO:
//
// Stricter verify?  Either require SunJSSE (assume its trust manager properly
// verifies whole chain), or implement our own TrustManager layer?
//
// What about v1/v3 and signing checks (re: man-in-the-middle attack),
// CRL check, basic constraints? notBefore? notAfter?
//
// Reference:  http://www.securitytracker.com/alerts/2002/Aug/1005030.html
//
// That is, we can't guarantee that installed/preferred provider trust manager
// implementations verify the whole chain properly and there are still
// v1 certs out there (i.e. have no basic constraints, etc.), meaning that
// we should check for and reject any intermediate certs that are not v3+
// (cannot be checked for basic constraints).  Only root and intermediate
// certs found in the trust store should be allowed to be v1 (since we must
// be trusting them for them to be there).  All other intermediate signers,
// however, should be required to be v3+, otherwise anybody with any kind
// of cert issued somehow via a trust chain from the root can pose as an
// intermediate signing CA and hence leave things open to man-in-the-middle
// style attack.  Also, we should really check CRLs, just in case
// it turns out that trust chain has been breached and thus issuer has revoked
// on some cert(s).  Of course, this really begs the question, as it is not
// guaranteed that all CAs in trust store have valid, working CRL URL
//
// So what to do?
//
// Maybe best to leave this all up to DBA?
        verify(host, socket.getSession());

        return socket;
    }
