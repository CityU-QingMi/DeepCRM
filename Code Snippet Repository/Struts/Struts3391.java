    public static String normalize(Object obj, boolean appendSpace) {
        Matcher matcher = WHITESPACE_BLOCK.matcher(StringUtils.trim(obj.toString()));
/**/
/**/
/**/
/**/
/**/
/**/
        return matcher.replaceAll("");
    }
