    public Set<Target> getTargets() {
        TreeSet<Target> targets = new TreeSet<Target>();

        // links
        matchPatterns(getLinkPattern(), targets, Link.TYPE_HREF);

        // actions
        matchPatterns(getActionPattern(), targets, Link.TYPE_ACTION);

        // forms
        matchPatterns(getFormPattern(), targets, Link.TYPE_FORM);

        return targets;
    }
