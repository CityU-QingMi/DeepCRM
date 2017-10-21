    public static void main(String[] args) throws Exception
    {
        String host="localhost";
        int port = 8080;
        
        HttpServer server = new JettyHttpServerProvider().createHttpServer(new
                InetSocketAddress(host, port), 10);
        server.start();
        
        final HttpContext httpContext = server.createContext("/",
                new HttpHandler()
        {

            public void handle(HttpExchange exchange) throws IOException
            {
                Headers responseHeaders = exchange.getResponseHeaders();
                responseHeaders.set("Content-Type","text/plain");
                exchange.sendResponseHeaders(200,0);

                OutputStream responseBody = exchange.getResponseBody();
                Headers requestHeaders = exchange.getRequestHeaders();
                Set<String> keySet = requestHeaders.keySet();
                Iterator<String> iter = keySet.iterator();
                while (iter.hasNext())
                {
                    String key = iter.next();
                    List values = requestHeaders.get(key);
                    String s = key + " = " + values.toString() + "\n";
                    responseBody.write(s.getBytes());
                }
                responseBody.close();

            }
        });
        
        httpContext.setAuthenticator(new BasicAuthenticator("Test")
        {
            @Override
            public boolean checkCredentials(String username, String password)
            {
                if ("username".equals(username) && password.equals("password"))
                    return true;
                return false;
            }
        });
          
        
        Thread.sleep(10000000);
                
    }
