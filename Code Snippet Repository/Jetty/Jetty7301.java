        @Override
        public void onWritePossible() throws IOException
        {
            // Dispatch OWP to another thread.
            executor.execute(() ->
            {
                while (output.isReady())
                {
                    if (responseWritten.compareAndSet(false, true))
                    {
                        try
                        {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType("text/plain;charset=utf-8");
                            output.write("Hello world".getBytes());
                        }
                        catch (IOException x)
                        {
                            throw new UncheckedIOException(x);
                        }
                    }
                    else
                    {
                        outputComplete.complete(null);
                        return;
                    }
                }
            });
        }
