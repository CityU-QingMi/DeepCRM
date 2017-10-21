        private boolean hasOpenQuote(String token)
        {
            int i = -1;
            do
            {
                int quote = getQuote(token, i + 1);
                if (quote < 0) { return false; }

                i = token.indexOf(quote, i + 1);
                i = token.indexOf(quote, i + 1);
            }
            while (i >= 0);
            return true;
        }
