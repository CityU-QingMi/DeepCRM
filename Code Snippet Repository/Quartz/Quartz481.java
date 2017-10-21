    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((compareTo == null) ? 0 : compareTo.hashCode());
        result = prime * result
                + ((compareWith == null) ? 0 : compareWith.hashCode());
        return result;
    }
