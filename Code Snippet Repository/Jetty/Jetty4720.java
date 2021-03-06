        public void add(String name, boolean value)
        {
            try
            {
                if (c == 0)
                    throw new IllegalStateException();
                _buffer.append(c);
                QuotedStringTokenizer.quote(_buffer,name);
                _buffer.append(':');
                appendBoolean(_buffer,value?Boolean.TRUE:Boolean.FALSE);
                c = ',';
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
