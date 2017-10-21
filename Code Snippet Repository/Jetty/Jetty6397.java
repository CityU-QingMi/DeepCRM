        @Override
        public String next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            String ret = token.toString();
            token.setLength(0);
            hasToken = false;
            return QuoteUtil.dequote(ret.trim());
        }
