    protected boolean isAccepted(String className) {
        if (!this.acceptClasses.isEmpty()) {
            for (Pattern pattern : acceptClasses) {
                Matcher matcher = pattern.matcher(className);
                if (matcher.matches()) {
                    return true;
                }
            }
            return false;
        } else
            return true;
    }
