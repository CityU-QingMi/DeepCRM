    public String build(String className) {
        String actionName = className;

        checkActionName(actionName);

        LOG.trace("Truncate Action suffix if found");
        actionName = truncateSuffixIfMatches(actionName);

        LOG.trace("Convert to underscores");
        char[] ca = actionName.toCharArray();
        StringBuilder build = new StringBuilder("" + ca[0]);
        boolean lower = true;
        for (int i = 1; i < ca.length; i++) {
            char c = ca[i];
            if (Character.isUpperCase(c) && lower) {
                build.append(separator);
                lower = false;
            } else if (!Character.isUpperCase(c)) {
                lower = true;
            }

            build.append(c);
        }

        actionName = build.toString();
        if (lowerCase) {
            actionName = actionName.toLowerCase();
        }

        LOG.trace("Changed action name from [{}] to [{}]", className, actionName);

        return actionName;
    }
