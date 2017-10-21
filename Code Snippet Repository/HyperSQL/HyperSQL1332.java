    public static String toPaddedString(String source, int length, String pad,
                                        boolean trailing) {

        int len = source.length();

        if (len == length) {
            return source;
        }

        if (len > length) {
            if (trailing) {
                return source.substring(0, length);
            } else {
                return source.substring(len - length, len);
            }
        }

        StringBuffer sb         = new StringBuffer(length);
        int          padLength  = source.length();
        int          partLength = (length - padLength) % pad.length();

        if (trailing) {
            sb.append(source);
            sb.append(pad.substring(pad.length() - partLength, pad.length()));
        }

        for (; padLength + pad.length() <= length; padLength += pad.length()) {
            sb.append(pad);
        }

        if (!trailing) {
            sb.append(pad.substring(0, partLength));
            sb.append(source);
        }

        return sb.toString();
    }
