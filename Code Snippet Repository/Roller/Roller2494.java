    public static String extractHTML(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder ret = new StringBuilder(str.length());
        int start = 0;
        int beginTag = str.indexOf('<');
        int endTag = 0;
        if (beginTag == -1) {
            return str;
        }

        while (beginTag >= start) {
            endTag = str.indexOf('>', beginTag);

            // if endTag found, keep tag
            if (endTag > -1) {
                ret.append(str.substring(beginTag, endTag + 1));

                // move start forward and find another tag
                start = endTag + 1;
                beginTag = str.indexOf('<', start);
            }
            // if no endTag found, break
            else {
                break;
            }
        }
        return ret.toString();
    }
