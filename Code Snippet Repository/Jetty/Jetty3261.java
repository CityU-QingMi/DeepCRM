        public void run()
        {
            try
            {
                int count = 0;
                while (!_done || count > 0)
                {
                    count = doRead();
                }
            }
            catch (IOException ex) { }
            catch (InterruptedException ex) { }
            finally
            {
                try
                {
                    _reader.close();
                }
                catch (IOException e) { }
            }
        }
