    private String getResponse(String sniHost, String reqHost, String cn) throws Exception
    {
        SslContextFactory clientContextFactory = new SslContextFactory(true);
        clientContextFactory.start();
        SSLSocketFactory factory = clientContextFactory.getSslContext().getSocketFactory();

        SSLSocket sslSocket = (SSLSocket)factory.createSocket("127.0.0.1", _port);

        if (cn != null)
        {
            SNIHostName serverName = new SNIHostName(sniHost);
            List<SNIServerName> serverNames = new ArrayList<>();
            serverNames.add(serverName);

            SSLParameters params = sslSocket.getSSLParameters();
            params.setServerNames(serverNames);
            sslSocket.setSSLParameters(params);
        }
        sslSocket.startHandshake();

        if (cn != null)
        {
            X509Certificate cert = ((X509Certificate)sslSocket.getSession().getPeerCertificates()[0]);
            Assert.assertThat(cert.getSubjectX500Principal().getName("CANONICAL"), Matchers.startsWith("cn=" + cn));
        }

        sslSocket.getOutputStream().write(("GET /ctx/path HTTP/1.0\r\nHost: " + reqHost + ":" + _port + "\r\n\r\n").getBytes(StandardCharsets.ISO_8859_1));
        String response = IO.toString(sslSocket.getInputStream());

        sslSocket.close();
        clientContextFactory.stop();
        return response;
    }
