    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        switch (type) {

            case StatementTypes.RETURN :
                return sql;

            case StatementTypes.CONDITION :
                sb.append(expression.getSQL());
                break;
        }

        return sb.toString();
    }
