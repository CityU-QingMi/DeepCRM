    int convertFromType(int type) {

        //Correct a bug in Informix JDBC driver that maps:
        // DATETIME YEAR TO FRACTION to TIME and
        // DATETIME HOUR TO SECOND to TIMESTAMP
        if (type == Types.TIMESTAMP) {
            type = Types.TIME;

            tracer.trace("Converted INFORMIX TIMESTAMP to TIME");
        } else if (type == Types.TIME) {
            type = Types.TIMESTAMP;

            tracer.trace("Converted INFORMIX TIME to TIMESTAMP");
        }

        return (type);
    }
