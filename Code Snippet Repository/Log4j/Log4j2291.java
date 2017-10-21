    private static String createRandomMessage(final String charset) {
        final char[] chars = charset.toCharArray();
        final StringBuilder sb = new StringBuilder();
        final Random random = new Random();
        for (int i = 0; i < random.nextInt(10000); i++) {
            final char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        final String output = sb.toString();
        return output;
    }
