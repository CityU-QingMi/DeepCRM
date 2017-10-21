        @Override
        public String encode(Quotes q) throws EncodeException
        {
            StringBuilder buf = new StringBuilder();
            buf.append("Author: ").append(q.getAuthor());
            buf.append(System.lineSeparator());
            for (String quote : q.quotes)
            {
                buf.append("Quote: ").append(quote);
                buf.append(System.lineSeparator());
            }
            return buf.toString();
        }
