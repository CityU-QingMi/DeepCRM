        @Override
        public void onDataAvailable() throws IOException
        {
            byte[] buffer = new byte[16 * 1024];
            while (input.isReady())
            {
                if (input.read(buffer) < 0)
                    return;
            }
        }
