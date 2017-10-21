    public String getSQL() {

        StringBuffer sb = new StringBuffer();

        switch (type) {

            case StatementTypes.ITERATE :
                sb.append(Tokens.T_ITERATE).append(' ').append(label);
                break;

            case StatementTypes.LEAVE :
                sb.append(Tokens.T_LEAVE).append(' ').append(label);
                break;
        }

        return sb.toString();
    }
