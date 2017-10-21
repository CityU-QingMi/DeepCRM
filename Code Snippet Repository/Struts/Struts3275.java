    protected List getIncludeProperties() {
        if (includeProperties != null && getDebug()) {
            List<Pattern> list = new ArrayList<>(includeProperties);
            list.add(Pattern.compile("debug"));
            list.add(WildcardUtil.compileWildcardPattern("error.*"));
            return list;
        } else {
            return includeProperties;
        }
    }
