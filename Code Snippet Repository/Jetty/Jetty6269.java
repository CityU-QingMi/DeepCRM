        @OnMessage
        public void echoed(InputStream input) throws IOException
        {
            while (true)
            {
                int read = input.read();
                if (read < 0)
                    break;
                output.write(read);
            }
            latch.countDown();
        }
