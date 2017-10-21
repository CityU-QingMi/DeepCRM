    protected HttpClientTransport provideClientTransport(Transport transport)
    {
        switch (transport)
        {
            case HTTP:
            case HTTPS:
            {
                return new HttpClientTransportOverHTTP(1);
            }
            case H2C:
            case H2:
            {
                HTTP2Client http2Client = newHTTP2Client();
                return new HttpClientTransportOverHTTP2(http2Client);
            }
            case FCGI:
            {
                return new HttpClientTransportOverFCGI(1, false, "");
            }
            default:
            {
                throw new IllegalArgumentException();
            }
        }
    }
