        public boolean isQuoteChar(char ch) {

            if (quoteChar == ch) {
                isInQuotes = !isInQuotes;

                return true;
            }

            return false;
        }
