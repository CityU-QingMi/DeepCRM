        public void complete()
        {
            try
            {
                if (c == '{')
                    _buffer.append("{}");
                else if (c != 0)
                    _buffer.append("}");
            }
            catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
