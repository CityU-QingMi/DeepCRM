    private boolean filtered(final StatusData data) {
        if (filters == null) {
            return false;
        }
        final String caller = data.getStackTraceElement().getClassName();
        for (final String filter : filters) {
            if (caller.startsWith(filter)) {
                return true;
            }
        }
        return false;
    }
