    Object getExtractValue(Session session, Object[] data) {

        if (data[1] == null) {
            return null;
        }

        int part = ((Number) nodes[0].valueData).intValue();

        part = DTIType.getFieldNameTypeForToken(part);

        switch (part) {

            case Types.SQL_INTERVAL_SECOND : {
                return ((DTIType) nodes[1].dataType).getSecondPart(data[1]);
            }
            case DTIType.MONTH_NAME :
            case DTIType.DAY_NAME : {
                return ((DateTimeType) nodes[1].dataType).getPartString(
                    session, data[1], part);
            }
            default : {
                int value = ((DTIType) nodes[1].dataType).getPart(session,
                    data[1], part);

                return ValuePool.getInt(value);
            }
        }
    }
