        public int abbreviate(final StringBuilder buf, final int startPos) {
            final int start = (startPos < 0) ? 0 : startPos;
            final int max = buf.length();
            int nextDot = -1;
            for (int i = start; i < max; i++) {
                if (buf.charAt(i) == '.') {
                    nextDot = i;
                    break;
                }
            }
            if (nextDot != -1) {
                if (nextDot - startPos > charCount) {
                    buf.delete(startPos + charCount, nextDot);
                    nextDot = startPos + charCount;

                    if (ellipsis != '\0') {
                        buf.insert(nextDot, ellipsis);
                        nextDot++;
                    }
                }
                nextDot++;
            }
            return nextDot;
        }
