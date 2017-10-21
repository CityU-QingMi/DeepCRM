        public void addClass(Class type)
        {
            try
            {
                if (c == 0)
                    throw new IllegalStateException();
                _buffer.append(c);
                _buffer.append("\"class\":");
                append(_buffer,type.getName());
                c = ',';
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
