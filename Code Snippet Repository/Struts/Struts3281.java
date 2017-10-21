    @SuppressWarnings("")
    protected List array() throws JSONException {
        List ret = new ArrayList();
        Object value = this.read();

        while (this.token != ARRAY_END) {
            ret.add(value);

            Object read = this.read();
            if (read == COMMA) {
                value = this.read();
            } else if (read != ARRAY_END) {
                throw buildInvalidInputException();
            }
        }

        return ret;
    }
