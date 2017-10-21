    int convertToType(int type) {

        if (type == Types.DECIMAL) {
            type = Types.NUMERIC;

            tracer.trace("Converted DECIMAL to NUMERIC");
        }

        return (type);
    }
