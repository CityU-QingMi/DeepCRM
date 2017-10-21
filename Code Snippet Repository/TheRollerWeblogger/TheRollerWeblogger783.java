    private void readRule(String str) {
        // check for bad condition
        if (StringUtils.isEmpty(str)) {
            return;
        }
        
        String rule = str.trim();

        // line has a comment?
        if (str.indexOf('#') > 0) {
            int commentLoc = str.indexOf('#');
            // strip comment
            rule = str.substring(0, commentLoc-1).trim();
        }

        // regex rule?
        if (rule.indexOf( '(' ) > -1) {
            // pre-compile patterns since they will be frequently used
            blacklistRegex.add(Pattern.compile(rule));
        } else if (StringUtils.isNotEmpty(rule)) {
            blacklistStr.add(rule);
        }
    }
