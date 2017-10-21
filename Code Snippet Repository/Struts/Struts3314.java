    public RPCError(Throwable t, int code, boolean debug) {
        while (t.getCause() != null) {
            t = t.getCause();
        }

        this.code = code;
        this.message = t.getMessage();
        this.name = t.getClass().getName();

        if (debug) {
            StringWriter s = new StringWriter();
            PrintWriter w = new PrintWriter(s);
            t.printStackTrace(w);
            w.flush();
            this.stack = s.toString();
        }

        LOG.error(t.getMessage(), t);
    }
