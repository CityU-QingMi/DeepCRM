    private NumberFormat getNumberFormat() {
        NumberFormat format = null;
        if (type == null) {
            try {
                type = findString(NUMBERTAG_PROPERTY);
            } catch (Exception e) {
                LOG.error("Could not find [" + NUMBERTAG_PROPERTY + "] on the stack!", e);
            }
        }
        if (type != null) {
            type = findString(type);
            if ("currency".equals(type)) {
                format = NumberFormat.getCurrencyInstance(ActionContext.getContext().getLocale());
            } else if ("integer".equals(type)) {
                format = NumberFormat.getIntegerInstance(ActionContext.getContext().getLocale());
            } else if ("number".equals(type)) {
                format = NumberFormat.getNumberInstance(ActionContext.getContext().getLocale());
            } else if ("percent".equals(type)) {
                format = NumberFormat.getPercentInstance(ActionContext.getContext().getLocale());
            }
        }
        if (format == null) {
            format = NumberFormat.getInstance(ActionContext.getContext().getLocale());
        }
        return format;
    }
