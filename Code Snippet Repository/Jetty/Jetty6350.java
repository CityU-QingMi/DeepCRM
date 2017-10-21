        @OnMessage
        public void echoed(Reader input) throws IOException
        {
            while (true)
            {
                int read = input.read();
                if (read < 0)
                    break;
                output.append((char)read);
            }
            latch.countDown();
        }
