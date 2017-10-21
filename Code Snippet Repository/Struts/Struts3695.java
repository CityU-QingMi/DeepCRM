    public String getPrefix() {
        if (parent == null) {
            return name;
        } else {
            String prefix = parent.getPrefix();
            if (prefix.equals("")) {
                return name;
            } else {
                return prefix + "_" + name;
            }
        }
    }
