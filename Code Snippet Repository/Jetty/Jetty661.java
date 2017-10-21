    @Before
    public void init() throws Exception
    {
        client = new HttpClient();
        client.start();
        destination = new HttpDestinationOverHTTP(client, new Origin("http", "localhost", 8080));
        destination.start();
        endPoint = new ByteArrayEndPoint();
        connection = new HttpConnectionOverHTTP(endPoint, destination, new Promise.Adapter<>());
        endPoint.setConnection(connection);
    }
