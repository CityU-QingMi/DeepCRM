    public String getFullName() {
        String prefix = "";
        if (parent != null) {
            String parentPrefix = parent.getPrefix();
            if (!parentPrefix.equals("")) {
                prefix = parentPrefix + "_";
            }
        }
        return prefix + cleanName();
    }
