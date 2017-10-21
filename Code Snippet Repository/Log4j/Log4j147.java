    protected EntryMessage entryMsg(final String format, final Object... params) {
        final int count = params == null ? 0 : params.length;
        if (count == 0) {
            if (Strings.isEmpty(format)) {
                return flowMessageFactory.newEntryMessage(null);
            }
            return flowMessageFactory.newEntryMessage(new SimpleMessage(format));
        }
        if (format != null) {
            return flowMessageFactory.newEntryMessage(new ParameterizedMessage(format, params));
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("params(");
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            final Object parm = params[i];
            sb.append(parm instanceof Message ? ((Message) parm).getFormattedMessage() : String.valueOf(parm));
        }
        sb.append(')');
        return flowMessageFactory.newEntryMessage(new SimpleMessage(sb));
    }
