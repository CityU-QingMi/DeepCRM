    private String filenameValue(String nameEqualsValue)
    {
        int idx = nameEqualsValue.indexOf('=');
        String value = nameEqualsValue.substring(idx+1).trim();

        if (value.matches(".??[a-z,A-Z]\\:\\\\[^\\\\].*"))
        {
            //incorrectly escaped IE filenames that have the whole path
            //we just strip any leading & trailing quotes and leave it as is
            char first=value.charAt(0);
            if (first=='"' || first=='\'')
                value=value.substring(1);
            char last=value.charAt(value.length()-1);
            if (last=='"' || last=='\'')
                value = value.substring(0,value.length()-1);

            return value;
        }
        else
            //unquote the string, but allow any backslashes that don't
            //form a valid escape sequence to remain as many browsers
            //even on *nix systems will not escape a filename containing
            //backslashes
            return QuotedStringTokenizer.unquoteOnly(value, true);
    }
