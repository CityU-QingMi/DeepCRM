    public synchronized String process(String path,String query,String content) throws Exception
    {
        String request = "GET /ctx/"+path+"/info";

        if (query!=null)
            request+="?"+query;
        request+=" HTTP/1.1\r\n"+
        "Host: localhost\r\n"+
        "Connection: close\r\n";
        if (content==null)
            request+="\r\n";
        else
        {
            request+="Content-Length: "+content.length()+"\r\n";
            request+="\r\n" + content;
        }

        int port=_port;
        try (Socket socket = new Socket("localhost",port))
        {
            socket.setSoTimeout(1000000);
            socket.getOutputStream().write(request.getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            String response = IO.toString(socket.getInputStream());
            __latch.await(1,TimeUnit.SECONDS);
            return response;
        }
        catch(Exception e)
        {
            System.err.println("failed on port "+port);
            e.printStackTrace();
            throw e;
        }
        
    }
