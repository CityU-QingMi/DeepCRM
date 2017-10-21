        private Source() throws IOException
        {
            if (inputFile != null)
            {
                inputFile.force(true);
                stream = new ChannelInputStream();
            }
            else
            {
                stream = new MemoryInputStream();
            }
            stream.reset();
        }
