    static int indexOfNonTabOrSpace(String line) {
        int pos = 0;
        int len = line.length();

        while (pos < len) {
            char ch = line.charAt(pos);

            if ((ch == ' ') || (ch == '\t')) {
                pos++;
                continue;
            }

            break;
        }

        return pos;
    }
