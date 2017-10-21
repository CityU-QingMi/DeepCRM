    private void test(final CountDownLatch latch, final List<String> failures)
    {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        // Choose a random destination
        String host = random.nextBoolean() ? "localhost" : "127.0.0.1";
        // Choose a random method
        HttpMethod method = random.nextBoolean() ? HttpMethod.GET : HttpMethod.POST;

        boolean ssl = isTransportSecure();

        // Choose randomly whether to close the connection on the client or on the server
        boolean clientClose = false;
        if (!ssl && random.nextInt(100) < 5)
            clientClose = true;
        boolean serverClose = false;
        if (!ssl && random.nextInt(100) < 5)
            serverClose = true;

        int maxContentLength = 64 * 1024;
        int contentLength = random.nextInt(maxContentLength) + 1;

        test(getScheme(), host, method.asString(), clientClose, serverClose, contentLength, true, latch, failures);
    }
