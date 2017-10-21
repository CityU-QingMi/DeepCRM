    private static int extractOptions(final String pattern, final int start, final List<String> options) {
        int i = start;
        while (i < pattern.length() && pattern.charAt(i) == '{') {
            final int begin = i++;
            int end;
            int depth = 0;
            do {
                end = pattern.indexOf('}', i);
                if (end == -1) {
                    break;
                }
                final int next = pattern.indexOf("{", i);
                if (next != -1 && next < end) {
                    i = end + 1;
                    ++depth;
                } else if (depth > 0) {
                    --depth;
                }
            } while (depth > 0);

            if (end == -1) {
                break;
            }

            final String r = pattern.substring(begin + 1, end);
            options.add(r);
            i = end + 1;
        }

        return i;
    }
