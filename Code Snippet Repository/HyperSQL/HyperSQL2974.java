    public DateTimeType getDateTimeTypeWithoutZone() {

        if (this.withTimeZone) {
            DateTimeType type;

            switch (typeCode) {

                case Types.SQL_TIME_WITH_TIME_ZONE :
                    type = new DateTimeType(Types.SQL_TIME, Types.SQL_TIME,
                                            scale);
                    break;

                case Types.SQL_TIMESTAMP_WITH_TIME_ZONE :
                    type = new DateTimeType(Types.SQL_TIMESTAMP,
                                            Types.SQL_TIMESTAMP, scale);
                    break;

                default :
                    throw Error.runtimeError(ErrorCode.U_S0500,
                                             "DateTimeType");
            }

            type.nameString = nameString;

            return type;
        }

        return this;
    }
