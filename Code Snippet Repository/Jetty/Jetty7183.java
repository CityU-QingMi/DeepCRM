    public Socket connect() throws IOException
    {
        socket = new Socket();
        socket.connect(endpoint,1000);

        out = socket.getOutputStream();
        in = socket.getInputStream();

        return socket;
    }
