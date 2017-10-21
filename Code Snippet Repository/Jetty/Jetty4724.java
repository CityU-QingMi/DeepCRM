        public void addJSON(Appendable buffer)
        {
            try
            {
                buffer.append(_json);
            }
            catch(IOException e)
            {
                throw new RuntimeException(e);
            }
        }
