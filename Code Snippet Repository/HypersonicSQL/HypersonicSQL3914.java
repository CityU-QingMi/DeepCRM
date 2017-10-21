    int convertFromType(int type) {

        if (type == 100) {
            type = Types.VARCHAR;

            tracer.trace("Converted HSQLDB VARCHAR_IGNORECASE to VARCHAR");
        }

        return (type);
    }
