    public boolean end(Writer writer, String body) {
        boolean end = super.end(writer, "", false);
        try {
            if (flush) {
                try {
                    writer.flush();
                } catch (IOException e) {
                	LOG.warn("error while trying to flush writer ", e);
                }
            }
            executeAction();

            if ((getVar() != null) && (proxy != null)) {
                getStack().setValue("#attr['" + getVar() + "']", proxy.getAction());
            }
        } finally {
            popComponentStack();
        }
        return end;
    }
