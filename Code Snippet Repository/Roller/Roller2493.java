    private static String stripLineBreaks(String str) {
        // TODO: use a string buffer, ignore case !
        str = str.replaceAll("<br>", "");
        str = str.replaceAll("<br/>", "");
        str = str.replaceAll("<br />", "");
        str = str.replaceAll("<p></p>", "");
        str = str.replaceAll("<p/>", "");
        str = str.replaceAll("<p />", "");
        return str;
    }
