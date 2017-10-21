    @Test
    public void testHandler()
        throws Exception
    {
        _handler.setWhite(_white.split(";",-1));
        _handler.setBlack(_black.split(";",-1));
        _handler.setWhiteListByPath(_byPath);

        String request = "GET " + _uri + " HTTP/1.1\n" + "Host: "+ _host + "\n\n";
        Socket socket = new Socket("127.0.0.1", _connector.getLocalPort());
        socket.setSoTimeout(5000);
        try
        {
            OutputStream output = socket.getOutputStream();
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            Response response = readResponse(input);
            Object[] params = new Object[]{
                    "Request WBHUC", _white, _black, _host, _uri, _code,
                    "Response", response.getCode()};
            assertEquals(Arrays.deepToString(params), _code, response.getCode());
        }
        finally
        {
            socket.close();
        }
    }
