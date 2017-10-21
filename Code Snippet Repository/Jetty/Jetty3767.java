    @Parameters(name = "")
    public static List<Object[]> data()
    {
        List<Object[]> data = new ArrayList<>();

        data.add(new Object[] { new HelloHandler(), "/test/", "GET /test/ HTTP/1.1 200" });
        data.add(new Object[] { new AsyncOnTimeoutCompleteHandler(), "/test/", "GET /test/ HTTP/1.1 200" });
        data.add(new Object[] { new AsyncOnTimeoutCompleteUnhandledHandler(), "/test/", "GET /test/ HTTP/1.1 200" });
        data.add(new Object[] { new AsyncOnTimeoutDispatchHandler(), "/test/", "GET /test/ HTTP/1.1 200" });

        data.add(new Object[] { new AsyncOnStartIOExceptionHandler(), "/test/", "GET /test/ HTTP/1.1 500" });
        data.add(new Object[] { new ResponseSendErrorHandler(), "/test/", "GET /test/ HTTP/1.1 500" });
        data.add(new Object[] { new ServletExceptionHandler(), "/test/", "GET /test/ HTTP/1.1 500" });
        data.add(new Object[] { new IOExceptionHandler(), "/test/", "GET /test/ HTTP/1.1 500" });
        data.add(new Object[] { new RuntimeExceptionHandler(), "/test/", "GET /test/ HTTP/1.1 500" });

        return data;
    }
