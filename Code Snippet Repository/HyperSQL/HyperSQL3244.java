    int convertToType(int type) {

        //Correct a bug in Informix JDBC driver that maps:
        // DATETIME YEAR TO FRACTION to TIME and
        // DATETIME HOUR TO SECOND to TIMESTAMP
        if (type == Types.TIMESTAMP) {
            type = Types.TIME;

            tracer.trace("Converted TIMESTAMP to INFORMIX TIME");
        } else if (type == Types.TIME) {
            type = Types.TIMESTAMP;

            tracer.trace("Converted TIME to INFORMIX TIMESTAMP");
        }

        return (type);
    }
