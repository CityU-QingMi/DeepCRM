    protected boolean end(Writer writer, String body, boolean popComponentStack) {
        assert(body != null);

        try {
            writer.write(body);
        } catch (IOException e) {
            throw new StrutsException("IOError while writing the body: " + e.getMessage(), e);
        }
        if (popComponentStack) {
            popComponentStack();
        }
        return false;
    }
