    public static String obfuscateEmail(String str) {
        Matcher emailMatch = EMAIL_PATTERN.matcher(str);
        while (emailMatch.find()) {
            String at = emailMatch.group(1);
            //System.out.println("at=" + at);
            str = str.replaceFirst(at, "-AT-");
            
            String dot = emailMatch.group(2) + emailMatch.group(3) + emailMatch.group(4);
            String newDot = emailMatch.group(2) + "-DOT-" + emailMatch.group(4);
            //System.out.println("dot=" + dot);
            str = str.replaceFirst(dot, newDot);
        }
        return str;
    }
