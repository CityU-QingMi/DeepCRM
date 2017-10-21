    @Test
    public void testServerDirectlyHTTP11() throws Exception
    {
        Socket socket = new Socket("127.0.0.1",((NetworkConnector)_server.getConnectors()[0]).getLocalPort());
        byte[] bytes = __message.getBytes(StandardCharsets.UTF_8);

        _received=null;
        socket.getOutputStream().write(
                ("POST /test/ HTTP/1.1\r\n"+
                "Host: 127.0.0.1:"+((NetworkConnector)_server.getConnectors()[0]).getLocalPort()+"\r\n"+
                "Content-Length: "+bytes.length+"\r\n"+
                "\r\n").getBytes("UTF-8"));
        socket.getOutputStream().write(bytes);
        socket.getOutputStream().flush();

        Thread.sleep(100);
        
        byte[] buf=new byte[4096];
        int len=socket.getInputStream().read(buf);
        String result=new String(buf,0,len,StandardCharsets.UTF_8);

        Assert.assertTrue(result.startsWith("HTTP/1.1 401 Unauthorized"));
        Assert.assertEquals(null,_received);
        
        int n=result.indexOf("nonce=");
        String nonce=result.substring(n+7,result.indexOf('"',n+7));
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b= md.digest(String.valueOf(System.currentTimeMillis()).getBytes(StringUtil.__ISO_8859_1));            
        String cnonce=encode(b);
        String digest="Digest username=\"testuser\" realm=\"test\" nonce=\""+nonce+"\" uri=\"/test/\" algorithm=MD5 response=\""+
        newResponse("POST","/test/",cnonce,"testuser","test","password",nonce,"auth")+
        "\" qop=auth nc="+NC+" cnonce=\""+cnonce+"\"";

        _received=null;
        socket.getOutputStream().write(
                ("POST /test/ HTTP/1.0\r\n"+
                "Host: 127.0.0.1:"+((NetworkConnector)_server.getConnectors()[0]).getLocalPort()+"\r\n"+
                "Content-Length: "+bytes.length+"\r\n"+
                "Authorization: "+digest+"\r\n"+
                "\r\n").getBytes("UTF-8"));
        socket.getOutputStream().write(bytes);
        socket.getOutputStream().flush();

        result = IO.toString(socket.getInputStream());

        Assert.assertTrue(result.startsWith("HTTP/1.1 200 OK"));
        Assert.assertEquals(__message,_received);
    }
