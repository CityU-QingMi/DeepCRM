        @Override
        public void write(String requestEntry) throws IOException
        {
            try
            {
                _log.exchange(requestEntry);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
