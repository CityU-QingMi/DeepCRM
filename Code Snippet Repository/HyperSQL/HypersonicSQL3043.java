    protected String checkConvertString(String s, String sep) {

        if (textFileSettings.isAllQuoted || s.length() == 0
                || s.indexOf(textFileSettings.quoteChar) != -1
                || (sep.length() > 0 && s.indexOf(sep) != -1)
                || hasUnprintable(s)) {
            s = StringConverter.toQuotedString(s, textFileSettings.quoteChar,
                                               true);
        }

        return s;
    }
