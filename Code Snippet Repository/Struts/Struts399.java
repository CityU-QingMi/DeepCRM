    public static Set<String> commaDelimitedStringToSet(String s) {
        Set<String> set = new HashSet<>();
        String[] split = s.split(",");
        for (String aSplit : split) {
            String trimmed = aSplit.trim();
            if (trimmed.length() > 0)
                set.add(trimmed);
        }
        return set;
    }
