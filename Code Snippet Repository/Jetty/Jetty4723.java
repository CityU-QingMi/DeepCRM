        private void getNext()
        {
            if (_next < 0)
            {
                try
                {
                    _next = _reader.read();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }
            }
        }
