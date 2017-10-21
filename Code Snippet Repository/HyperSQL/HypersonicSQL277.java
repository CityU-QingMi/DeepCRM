    public String getSQL() {

        StringBuffer sb = new StringBuffer(128);

        sb.append(Tokens.T_CREATE).append(' ');
        sb.append(Tokens.T_SEQUENCE).append(' ');
        sb.append(getName().getSchemaQualifiedStatementName()).append(' ');
        sb.append(Tokens.T_AS).append(' ');
        sb.append(getDataType().getNameString()).append(' ');

        //
        sb.append(Tokens.T_START).append(' ');
        sb.append(Tokens.T_WITH).append(' ');
        sb.append(startValue);

        if (getIncrement() != 1) {
            sb.append(' ').append(Tokens.T_INCREMENT).append(' ');
            sb.append(Tokens.T_BY).append(' ');
            sb.append(getIncrement());
        }

        if (!hasDefaultMinMax()) {
            sb.append(' ').append(Tokens.T_MINVALUE).append(' ');
            sb.append(getMinValue());
            sb.append(' ').append(Tokens.T_MAXVALUE).append(' ');
            sb.append(getMaxValue());
        }

        if (isCycle()) {
            sb.append(' ').append(Tokens.T_CYCLE);
        }

        if (name == null) {
            sb.append(Tokens.T_CLOSEBRACKET);
        }

        return sb.toString();
    }
