        public void add(String name, long value)
        {
            try
            {
                if (c == 0)
                    throw new IllegalStateException();
                _buffer.append(c);
                QuotedStringTokenizer.quote(_buffer,name);
                _buffer.append(':');
                appendNumber(_buffer, value);
                c = ',';
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
