    @SuppressWarnings("")
    protected Map object() throws JSONException {
        Map ret = new HashMap();
        Object next = this.read();
        if (next != OBJECT_END) {
            String key = (String) next;
            while (this.token != OBJECT_END) {
                this.read(); // should be a colon

                if (this.token != OBJECT_END) {
                    ret.put(key, this.read());

                    if (this.read() == COMMA) {
                        Object name = this.read();

                        if (name instanceof String) {
                            key = (String) name;
                        } else
                            throw buildInvalidInputException();
                    }
                }
            }
        }

        return ret;
    }
