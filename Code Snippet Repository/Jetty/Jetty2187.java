        private int getQuote(String token, int offset)
        {
            int i = token.indexOf('"', offset);
            int j = token.indexOf('\'', offset);
            if (i < 0)
            {
                if (j < 0)
                {
                    return -1;
                }
                else
                {
                    return '\'';
                }
            }
            if (j < 0) { return '"'; }
            if (i < j) { return '"'; }
            return '\'';
        }
