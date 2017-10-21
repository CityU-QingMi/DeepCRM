    private boolean allow(String ognl) {
        Map<String, Filter> includesExcludesMap = getIncludesExcludesMap();

        boolean allow = !isDefaultBlock();

        if (includesExcludesMap != null) {
            for (String currRule : includesExcludesMap.keySet()) {
                Filter f = includesExcludesMap.get(currRule);
                if (f.pattern.matcher(ognl).matches()) {
                    allow = f.allow;
                }
            }
        }

        return allow;
    }
