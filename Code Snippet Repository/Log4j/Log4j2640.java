    void write(final byte[] bytes) {
        if (raw) {
            data.add(bytes);
            return;
        }
        final String str = new String(bytes);
        if (newLine) {
            int index = 0;
            while (index < str.length()) {
                int end;
                final int wend = str.indexOf(WINDOWS_LINE_SEP, index);
                final int lend = str.indexOf('\n', index);
                int length;
                if (wend >= 0 && wend < lend) {
                    end = wend;
                    length = 2;
                } else {
                    end = lend;
                    length = 1;
                }
                if (index == end) {
                    if (!messages.get(messages.size() - length).isEmpty()) {
                        messages.add("");
                    }
                } else if (end >= 0) {
                    messages.add(str.substring(index, end));
                } else {
                    messages.add(str.substring(index));
                    break;
                }
                index = end + length;
            }
        } else {
            messages.add(str);
        }
    }
