    private void upload(String filename) throws Exception
    {
        File inputFile = MavenTestingUtils.getTestResourceFile("data/" + filename);

        WebSocketContainer client = ContainerProvider.getWebSocketContainer();
        ClientSocket socket = new ClientSocket();
        URI uri = serverUri.resolve("/upload/" + filename);
        client.connectToServer(socket,uri);
        socket.uploadFile(inputFile);
        socket.awaitClose();

        File sha1File = MavenTestingUtils.getTestResourceFile("data/" + filename + ".sha");
        assertFileUpload(new File(outputDir,filename),sha1File);
    }
