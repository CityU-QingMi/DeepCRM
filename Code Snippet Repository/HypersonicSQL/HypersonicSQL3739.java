    public Type getCombinedType(Session session, Type other, int operation) {

        switch (operation) {

            case OpTypes.MULTIPLY :
                if (other.isNumberType()) {
                    return getIntervalType(this, maxIntervalPrecision, scale);
                }
                break;

            case OpTypes.DIVIDE :
                if (other.isNumberType()) {
                    return this;
                } else if (other.isIntervalType()) {
                    IntervalType otherType = (IntervalType) other;

                    if (isYearMonth == otherType.isYearMonth) {
                        return isYearMonth ? Type.SQL_BIGINT
                                           : factorType;
                    }
                }
                break;

            case OpTypes.ADD :
                if (other.isDateTimeType()) {
                    return other.getCombinedType(session, this, operation);
                } else if (other.isIntervalType()) {
                    IntervalType newType =
                        (IntervalType) getAggregateType(other);

                    return getIntervalType(newType, maxIntervalPrecision, 0);
                }
                break;

            case OpTypes.SUBTRACT :
            default :
                return getAggregateType(other);
        }

        throw Error.error(ErrorCode.X_42562);
    }
