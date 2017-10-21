    private Collection<String> prepareExcludes() {
        Collection<String> localExcludes = excludes;
        if (!copyErrors || !copyMessages ||!copyFieldErrors) {
            if (localExcludes == null) {
                localExcludes = new HashSet<String>();
                if (!copyErrors) {
                    localExcludes.add(ACTION_ERRORS);
                }
                if (!copyMessages) {
                    localExcludes.add(ACTION_MESSAGES);
                }
                if (!copyFieldErrors) {
                    localExcludes.add(FIELD_ERRORS);
                }
            }
        }
        return localExcludes;
    }
