        public static void main(String[] args) throws Exception
        {
            QueuedThreadPool clientThreads = new QueuedThreadPool();
            clientThreads.setName("client");
            HttpClient client = new HttpClient(new HttpClientTransportOverHTTP(2), null);
            client.setMaxConnectionsPerDestination(2);
            client.setIdleTimeout(10000);
            client.setExecutor(clientThreads);
            client.start();

            Random random = new Random();

            while (true)
            {
                int count = 1;
                final CountDownLatch latch = new CountDownLatch(count);
                for (int i = 0; i < count; ++i)
                {
                    int length = 16 * 1024 * 1024 + random.nextInt(16 * 1024 * 1024);
                    client.newRequest("localhost", 8888)
                            .content(new BytesContentProvider(new byte[length]))
                            .send(result -> latch.countDown());
                    long sleep = 1 + random.nextInt(10);
                    TimeUnit.MILLISECONDS.sleep(sleep);
                }
                latch.await();
            }
        }
