    private <B extends Appendable> B applyRules(final Calendar calendar, final B buf) {
        try {
            for (final Rule rule : mRules) {
                rule.appendTo(buf, calendar);
            }
        } catch (final IOException ioe) {
            Throwables.rethrow(ioe);
        }
        return buf;
    }
