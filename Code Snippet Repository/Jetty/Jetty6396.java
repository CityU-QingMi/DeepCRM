        private void appendToken(char c)
        {
            if (hasToken)
            {
                token.append(c);
            }
            else
            {
                if (Character.isWhitespace(c))
                {
                    return; // skip whitespace at start of token.
                }
                else
                {
                    token.append(c);
                    hasToken = true;
                }
            }
        }
