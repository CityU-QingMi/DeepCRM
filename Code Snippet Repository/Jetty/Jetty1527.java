        @Override
        public boolean content(ByteBuffer ref)
        {
            try
            {
                _content.write(BufferUtil.toArray(ref));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
            return false;
        }
