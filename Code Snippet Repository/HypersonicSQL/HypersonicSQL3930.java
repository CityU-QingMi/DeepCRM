    int convertFromType(int type) {

        // MS SQL 7 specific problems (Northwind database)
        if (type == 11) {
            tracer.trace("Converted DATETIME (type 11) to TIMESTAMP");

            type = Types.TIMESTAMP;
        } else if (type == -9) {
            tracer.trace("Converted NVARCHAR (type -9) to VARCHAR");

            type = Types.VARCHAR;
        } else if (type == -8) {
            tracer.trace("Converted NCHAR (type -8) to VARCHAR");

            type = Types.VARCHAR;
        } else if (type == -10) {
            tracer.trace("Converted NTEXT (type -10) to VARCHAR");

            type = Types.VARCHAR;
        } else if (type == -1) {
            tracer.trace("Converted LONGTEXT (type -1) to LONGVARCHAR");

            type = Types.LONGVARCHAR;
        }

        return (type);
    }
