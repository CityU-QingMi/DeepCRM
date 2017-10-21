        @Override
        public final void appendTo(final Appendable buffer, final int value) throws IOException {
            if (value < 10) {
                buffer.append((char)(value + '0'));
            } else if (value < 100) {
                appendDigits(buffer, value);
            } else {
               appendFullDigits(buffer, value, 1);
            }
        }
