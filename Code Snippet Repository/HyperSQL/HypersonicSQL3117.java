    static String echoBackReplyString(String inCommand, int retval) {
        String uc = inCommand.trim().toUpperCase(Locale.ENGLISH);
        int firstWhiteSpace;
        for (firstWhiteSpace = 0; firstWhiteSpace < uc.length();
            firstWhiteSpace++) {
            if (Character.isWhitespace(uc.charAt(firstWhiteSpace))) {
                break;
            }
        }
        StringBuffer replyString = new StringBuffer(
            uc.substring(0, firstWhiteSpace));
        String keyword = replyString.toString();
        if (keyword.equals("UPDATE") || keyword.equals("DELETE")) {
            replyString.append(" " + retval);
        } else if (keyword.equals("CREATE") || keyword.equals("DROP")) {
            // This case is significantly missing from the spec., yet
            // PostgreSQL Server echo's these commands as implemented here.
            // TODO: Add error-checking
            int wordStart;
            for (wordStart = firstWhiteSpace; wordStart < uc.length();
                wordStart++) {
                if (!Character.isWhitespace(uc.charAt(wordStart))) {
                    break;
                }
            }
            int wordEnd;
            for (wordEnd = wordStart; wordEnd < uc.length();
                wordEnd++) {
                if (!Character.isWhitespace(uc.charAt(wordEnd))) {
                    break;
                }
            }
            replyString.append(" " + uc.substring(wordStart, wordEnd));
        } else if (keyword.equals("INSERT")) {
            replyString.append(" " + 0 + ' ' + retval);
            // The number is the supposed to be the oid for single-row
            // inserts into a table that has row oids.
            // Since the requirement is conditional, it's very likely that the
            // client will make any use of the value we pass.
        }
        // If we ever implement following SQL commands, add echo's for these
        // strings too:  MOVE, FETCH, COPY.
        return replyString.toString();
    }
