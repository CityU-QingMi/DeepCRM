        @Override
        public String nextToken()
        {
            String token = super.nextToken();

            while (hasOpenQuote(token) && hasMoreTokens())
            {
                token += "," + super.nextToken();
            }
            return token;
        }
