    public void cleanupRequest(HttpServletRequest request) {
        Integer counterVal = (Integer) request.getAttribute(CLEANUP_RECURSION_COUNTER);
        if (counterVal != null) {
            counterVal -= 1;
            request.setAttribute(CLEANUP_RECURSION_COUNTER, counterVal);
            if (counterVal > 0 ) {
                LOG.debug("skipping cleanup counter={}", counterVal);
                return;
            }
        }
        // always clean up the thread request, even if an action hasn't been executed
        try {
            dispatcher.cleanUpRequest(request);
        } finally {
            ActionContext.setContext(null);
            Dispatcher.setInstance(null);
            devModeOverride.remove();
        }
    }
