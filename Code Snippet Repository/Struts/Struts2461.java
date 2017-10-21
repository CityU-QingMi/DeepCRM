    public String build(String className) {
        String actionName = className;

        checkActionName(actionName);

        LOG.trace("Truncate Action suffix if found");
        actionName = truncateSuffixIfMatches(actionName);

        LOG.trace("Force initial letter of action to lowercase, if desired");
        if ((lowerCase) && (actionName.length() > 1)) {
            int lowerPos = actionName.lastIndexOf('/') + 1;
            actionName = actionName.substring(0, lowerPos) +
                    Character.toLowerCase(actionName.charAt(lowerPos)) +
                    actionName.substring(lowerPos + 1);
        }

        return actionName;
    }
