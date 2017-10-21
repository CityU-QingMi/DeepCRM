    public String getSQL() {

        StringBuffer sb = new StringBuffer(64);
        String       s;

        s = handlerType == CONTINUE ? Tokens.T_CONTINUE
                                    : handlerType == EXIT ? Tokens.T_EXIT
                                                          : Tokens.T_UNDO;

        sb.append(Tokens.T_DECLARE).append(' ').append(s).append(' ');
        sb.append(Tokens.T_HANDLER).append(' ').append(Tokens.T_FOR);
        sb.append(' ');

        for (int i = 0; i < conditionStates.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }

            sb.append(Tokens.T_SQLSTATE).append(' ');
            sb.append('\'').append(conditionStates.get(i)).append('\'');
        }

        for (int i = 0; i < conditionGroups.size(); i++) {
            if (i > 0) {
                sb.append(',');
            }

            switch (conditionGroups.get(i)) {

                case SQL_EXCEPTION :
                    sb.append(Tokens.T_SQLEXCEPTION);
                    break;

                case SQL_WARNING :
                    sb.append(Tokens.T_SQLWARNING);
                    break;

                case SQL_NOT_FOUND :
                    sb.append(Tokens.T_NOT).append(' ').append(Tokens.FOUND);
                    break;
            }
        }

        if (statement != null) {
            sb.append(' ').append(statement.getSQL());
        }

        return sb.toString();
    }
