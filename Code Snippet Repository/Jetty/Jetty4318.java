    public HttpTester.Response executeRequest(String method, String path, int idleFor, TimeUnit idleUnit) throws Exception
    {
        HttpTester.Request request = HttpTester.newRequest();

        request.setMethod(method);
        request.setVersion("HTTP/1.1");
        request.setHeader("Host","tester");
        request.setHeader("Accept-Encoding",accept);
        request.setHeader("Connection","close");

        if (this.userAgent != null)
        {
            request.setHeader("User-Agent",this.userAgent);
        }
        
        request.setURI(path);

        // Issue the request
        return HttpTester.parseResponse(tester.getResponses(request.generate(),idleFor,idleUnit));
    }
