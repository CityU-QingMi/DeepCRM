    public String getSQL() {

        StringBuffer sb   = new StringBuffer(64);
        String       left = getContextSQL(nodes.length > 0 ? nodes[LEFT]
                                                           : null);

        switch (opType) {

            case OpTypes.ARRAY_AGG :
                sb.append(' ').append(Tokens.T_ARRAY_AGG).append('(');
                sb.append(left).append(')');
                break;

            case OpTypes.GROUP_CONCAT :
                sb.append(' ').append(Tokens.T_GROUP_CONCAT).append('(');
                sb.append(left).append(')');
                break;

            case OpTypes.MEDIAN :
                sb.append(' ').append(Tokens.T_MEDIAN).append('(');
                sb.append(left).append(')');
                break;

            default :
                throw Error.runtimeError(ErrorCode.U_S0500,
                                         "ExpressionAggregate");
        }

        return sb.toString();
    }
