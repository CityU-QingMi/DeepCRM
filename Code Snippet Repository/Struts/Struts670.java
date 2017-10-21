    private List<Pattern> buildExcludedPatternsList( String patterns ) {
        if (null != patterns && patterns.trim().length() != 0) {
            List<Pattern> list = new ArrayList<>();
            String[] tokens = patterns.split(",");
            for ( String token : tokens ) {
                list.add(Pattern.compile(token.trim()));
            }
            return Collections.unmodifiableList(list);
        } else {
            return null;
        }
    }
