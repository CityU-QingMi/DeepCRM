    protected boolean shouldExcludeProperty(String expr) {
        if (this.excludeProperties != null) {
            for (Pattern pattern : this.excludeProperties) {
                if (pattern.matcher(expr).matches()) {
                    if (LOG.isDebugEnabled()) {
                        LOG.debug("Ignoring property because of exclude rule: " + expr);
                    }
                    return true;
                }
            }
        }

        if (this.includeProperties != null) {
            for (Pattern pattern : this.includeProperties) {
                if (pattern.matcher(expr).matches()) {
                    return false;
                }
            }
            if (LOG.isDebugEnabled()){
                LOG.debug("Ignoring property because of include rule:  " + expr);
            }
            return true;
        }
        return false;
    }
