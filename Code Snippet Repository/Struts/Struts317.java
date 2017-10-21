    protected Object handleOgnlException(String expr, boolean throwExceptionOnFailure, OgnlException e) {
        Object ret = findInContext(expr);
        if (ret == null) {
            if (shouldLogMissingPropertyWarning(e)) {
                LOG.warn("Could not find property [{}]!", expr, e);
            }
            if (throwExceptionOnFailure) {
                throw new XWorkException(e);
            }
        }
        return ret;
    }
