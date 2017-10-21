    boolean scanSeparator() {

        boolean result = false;

        while (true) {
            boolean whiteSpace = scanWhitespace();

            result |= whiteSpace;

            if (scanCommentAsInlineSeparator()) {
                result               = true;
                hasNonSpaceSeparator = true;

                continue;
            }

            break;
        }

//        token.isDelimiter |= result;
        return result;
    }
