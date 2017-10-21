    public CachedDateFormat(final DateFormat dateFormat, final int expiration) {
        if (dateFormat == null) {
            throw new IllegalArgumentException("dateFormat cannot be null");
        }

        if (expiration < 0) {
            throw new IllegalArgumentException("expiration must be non-negative");
        }

        formatter = dateFormat;
        this.expiration = expiration;
        millisecondStart = 0;

        //
        //   set the previousTime so the cache will be invalid
        //        for the next request.
        previousTime = Long.MIN_VALUE;
        slotBegin = Long.MIN_VALUE;
    }
