        boolean filter(final Level level, final Marker marker, final String msg, final Object p0,
                final Object p1, final Object p2, final Object p3,
                final Object p4, final Object p5, final Object p6,
                final Object p7, final Object p8) {
            final Filter filter = config.getFilter();
            if (filter != null) {
                final Filter.Result r = filter.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8);
                if (r != Filter.Result.NEUTRAL) {
                    return r == Filter.Result.ACCEPT;
                }
            }
            return level != null && intLevel >= level.intLevel();
        }
