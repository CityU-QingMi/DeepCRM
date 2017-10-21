        public void setContent(ByteBuffer content)
        {
            try
            {
                _content=new ByteArrayOutputStream();
                _content.write(BufferUtil.toArray(content));
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
