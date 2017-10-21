        public void setContent(byte[] bytes)
        {
            try
            {
                _content=new ByteArrayOutputStream();
                _content.write(bytes);
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
