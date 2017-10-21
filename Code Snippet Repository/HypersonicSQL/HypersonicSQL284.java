    private long getDefaultMinOrMax(boolean isMax) {

        long min;
        long max;

        switch (dataType.typeCode) {

            case Types.TINYINT :
                max = Byte.MAX_VALUE;
                min = Byte.MIN_VALUE;
                break;

            case Types.SQL_SMALLINT :
                max = Short.MAX_VALUE;
                min = Short.MIN_VALUE;
                break;

            case Types.SQL_INTEGER :
                max = Integer.MAX_VALUE;
                min = Integer.MIN_VALUE;
                break;

            case Types.SQL_BIGINT :
                max = Long.MAX_VALUE;
                min = Long.MIN_VALUE;
                break;

            case Types.SQL_NUMERIC :
            case Types.SQL_DECIMAL :
                max = Long.MAX_VALUE;
                min = Long.MIN_VALUE;
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500, "NumberSequence");
        }

        return isMax ? max
                     : min;
    }
