    public static String encodeEmail(String str) {
        // obfuscate mailto's: turns them into hex encoded,
        // so that browsers can still understand the mailto link
        Matcher mailtoMatch = MAILTO_PATTERN.matcher(str);
        while (mailtoMatch.find()) {
            String email = mailtoMatch.group(1);
            //System.out.println("email=" + email);
            String hexed = encode(email);
            str = str.replaceFirst("mailto:"+email, "mailto:"+hexed);
        }
        
        return obfuscateEmail(str);
    }
