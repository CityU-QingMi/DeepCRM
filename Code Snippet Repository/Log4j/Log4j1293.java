    private String formatDate(final long date, final String format) {
        DateFormat dateFormat = null;
        if (format != null) {
            try {
                dateFormat = new SimpleDateFormat(format);
            } catch (final Exception ex) {
                LOGGER.error(LOOKUP, "Invalid date format: [{}], using default", format, ex);
            }
        }
        if (dateFormat == null) {
            dateFormat = DateFormat.getInstance();
        }
        return dateFormat.format(new Date(date));
    }
