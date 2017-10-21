    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        switch (operationType) {

            case StatementSet.TRIGGER_SET :
                return sql;

            case StatementSet.VARIABLE_SET : {

                /** @todo - cover row assignment */
                sb.append(Tokens.T_SET).append(' ');
                sb.append(targets[0].getColumn().getName().statementName);
                sb.append(' ').append('=').append(' ').append(
                    expression.getSQL());

                break;
            }
        }

        return sb.toString();
    }
