        protected int doRead() throws IOException, InterruptedException
        {
            if (!_reader.ready())
            {
                Thread.sleep(25);
            }

            int count = 0;
            if (_reader.ready())
            {
                count = _reader.read(_buffer);
                if (count > 0)
                {
                    _response.append(_buffer, 0, count);
                }
            }

            return count;
        }
