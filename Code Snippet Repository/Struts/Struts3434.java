    protected String getVersionFromString(String str) {
        Matcher matcher = versionPattern.matcher(str);
        List<String> parts = new ArrayList<String>();
        while (matcher.find()) {
            parts.add(matcher.group(1));
        }
        //default
        if (parts.size() == 0) {
            return "1.0.0";
        }
        while (parts.size() < 3) {
            parts.add("0");
        }
        return StringUtils.join(parts, ".");
    }
