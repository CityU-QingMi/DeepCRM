    protected boolean isAccepted(String name) {
        AcceptedPatternsChecker.IsAccepted accepted = acceptedPatternsChecker.isAccepted(name);
        if (accepted.isAccepted()) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("Cookie [{}] matches acceptedPattern [{}]", name, accepted.getAcceptedPattern());
            }
            return true;
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("Cookie [{}] doesn't match acceptedPattern [{}]", name, accepted.getAcceptedPattern());
        }
        return false;
    }
