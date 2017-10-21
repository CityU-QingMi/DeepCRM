    protected boolean decide(Object element) {
        if (decider != null) {
            try {
                boolean okToAdd = decider.decide(element);
                return okToAdd;
            }
            catch(Exception e) {
                LOG.warn("Decider [{}] encountered an error while decide adding element [{}], element will be ignored, it will not appeared in subseted iterator",
                            decider, element, e);
                return false;
            }
        }
        return true;
    }
