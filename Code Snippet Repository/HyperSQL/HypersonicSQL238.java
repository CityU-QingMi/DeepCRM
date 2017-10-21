        public boolean next(char ch, int position) {

            int index = ++offset;
            int len   = offset + 1;
            int left  = 0;

            matched = false;

            for (int i = tokens.length; --i >= 0; ) {
                if (isZeroBit(i)) {
                    if (tokens[i][index] == Character.toUpperCase(ch)) {
                        if (tokens[i].length == len) {
                            setBit(i);

                            lastMatched = i;
                            consumed    = true;
                            matched     = true;
                            matchOffset = position;
                        } else {
                            ++left;
                        }
                    } else {
                        setBit(i);
                    }
                }
            }

            return left > 0;
        }
