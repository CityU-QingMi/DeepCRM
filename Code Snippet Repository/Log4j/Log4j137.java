    protected void validateKey(final String key) {
        if (maxLength > 0 && key.length() > maxLength) {
            throw new IllegalArgumentException("Structured data keys are limited to " + maxLength +
                    " characters. key: " + key);
        }
        for (int i = 0; i < key.length(); i++) {
            final char c = key.charAt(i);
            if (c < '!' || c > '~' || c == '=' || c == ']' || c == '"') {
                throw new IllegalArgumentException("Structured data keys must contain printable US ASCII characters" +
                        "and may not contain a space, =, ], or \"");
            }
        }
    }
