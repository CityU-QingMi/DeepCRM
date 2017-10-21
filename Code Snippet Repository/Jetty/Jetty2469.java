        @Override
        public void run()
        {
            String threadName = Thread.currentThread().getName();
            LOG.info("Starting thread {}", threadName);
            try
            {
                while (success.get())
                {
                    --iterations;

                    byte[] content = new byte[1024];
                    new Random().nextBytes(content);
                    ContentResponse response = client.newRequest(host, port).method(HttpMethod.POST).content(new BytesContentProvider(content))
                            .timeout(5, TimeUnit.SECONDS).send();

                    if (response.getStatus() != 200)
                    {
                        LOG.warn("Got response <{}>, expecting <{}> iteration=", response.getStatus(), 200,iterations);
                        // allow all ClientLoops to finish
                        success.set(false);
                    }
                    else
                    {
                        if (iterations == 0)
                            break;
                    }
                }
            }
            catch (Throwable x)
            {
                LOG.warn("Error processing request "+iterations, x);
                success.set(false);
            }
            finally
            {
                LOG.info("Shutting down thread {}", threadName);
                active.countDown();
            }
        }
