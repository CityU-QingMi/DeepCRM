    public void issueHandshake() throws IOException
    {
        StringBuilder req = new StringBuilder();
        req.append("GET ").append(uri.getPath()).append(" HTTP/1.1\r\n");
        req.append("Upgrade: WebSocket\r\n");
        req.append("Connection: Upgrade\r\n");
        req.append("Host: ").append(uri.getHost()).append(":").append(uri.getPort()).append("\r\n");
        req.append("Origin: http://www.google.com/\r\n");
        req.append("Sec-WebSocket-Key1: 15{ft  :6@87  0 M 5 c901\r\n");
        req.append("Sec-WebSocket-Key2: 3? C;7~0 8   \" 3 2105 6  `_ {\r\n");
        req.append("\r\n");

        // System.out.printf("--- Request ---%n%s",req);

        byte reqBytes[] = req.toString().getBytes(StandardCharsets.UTF_8);
        byte hixieBytes[] = TypeUtil.fromHexString("e739617916c9daf3");
        byte buf[] = new byte[reqBytes.length + hixieBytes.length];
        System.arraycopy(reqBytes,0,buf,0,reqBytes.length);
        System.arraycopy(hixieBytes,0,buf,reqBytes.length,hixieBytes.length);

        // Send HTTP GET Request (with hixie bytes)
        out.write(buf,0,buf.length);
        out.flush();

        // Read HTTP 101 Upgrade / Handshake Response
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        socket.setSoTimeout(5000);

        boolean foundEnd = false;
        String line;
        while (!foundEnd)
        {
            line = br.readLine();
            // System.out.printf("RESP: %s%n",line);
            Assert.assertThat(line, notNullValue());
            if (line.length() == 0)
            {
                foundEnd = true;
            }
        }

        // Read expected handshake hixie bytes
        byte hixieHandshakeExpected[] = TypeUtil.fromHexString("c7438d956cf611a6af70603e6fa54809");
        byte hixieHandshake[] = new byte[hixieHandshakeExpected.length];

        int readLen = in.read(hixieHandshake,0,hixieHandshake.length);
        Assert.assertThat("Read hixie handshake bytes",readLen,is(hixieHandshake.length));
    }
