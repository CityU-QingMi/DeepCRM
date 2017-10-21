    public void format(final int fieldStart, final StringBuilder buffer) {
        final int rawLength = buffer.length() - fieldStart;

        if (rawLength > maxLength) {
			if (leftTruncate) {
				buffer.delete(fieldStart, buffer.length() - maxLength);
			} else {
				buffer.delete(fieldStart + maxLength, fieldStart + buffer.length());
			}
        } else if (rawLength < minLength) {
            if (leftAlign) {
                final int fieldEnd = buffer.length();
                buffer.setLength(fieldStart + minLength);

                for (int i = fieldEnd; i < buffer.length(); i++) {
                    buffer.setCharAt(i, ' ');
                }
            } else {
                int padLength = minLength - rawLength;

                for (; padLength > SPACES.length; padLength -= SPACES.length) {
                    buffer.insert(fieldStart, SPACES);
                }

                buffer.insert(fieldStart, SPACES, 0, padLength);
            }
        }
    }
