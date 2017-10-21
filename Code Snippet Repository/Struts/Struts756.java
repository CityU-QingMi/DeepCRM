    protected boolean isExcluded(String name) {
        ExcludedPatternsChecker.IsExcluded excluded = excludedPatternsChecker.isExcluded(name);
        if (excluded.isExcluded()) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("Cookie [{}] matches excludedPattern [{}]", name, excluded.getExcludedPattern());
            }
            return true;
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("Cookie [{}] doesn't match excludedPattern [{}]", name, excluded.getExcludedPattern());
        }
        return false;
    }
