        public void run()
        {
            try
            {
                // Wait for all workers to be ready
                barrier.await();

                Random random = new Random( System.nanoTime() );

                for ( int i = 0; i < requestsCount; ++i )
                {
                    int pauseMsec = random.nextInt(1000);

                    //wait a random number of milliseconds between requests up to 1 second
                    if (pauseMsec > 0)
                    {
                        Thread.currentThread().sleep(pauseMsec);
                    }
                    Request request = client.newRequest(url + "?action=increment");
                    ContentResponse response = request.send();
                    assertEquals(HttpServletResponse.SC_OK,response.getStatus());
                }

                // Wait for all workers to be done
                barrier.await();
            }
            catch ( Exception x )
            {
                throw new RuntimeException( x );
            }
        }
