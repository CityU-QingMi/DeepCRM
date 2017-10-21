    public String getSQL() {

        StringBuffer sb = getSQLMain();

        if (maxRowsQueued != 0) {
            sb.append(Tokens.T_QUEUE).append(' ');
            sb.append(maxRowsQueued).append(' ');

            if (nowait) {
                sb.append(Tokens.T_NOWAIT).append(' ');
            }
        }

        sb.append(Tokens.T_CALL).append(' ');
        sb.append(StringConverter.toQuotedString(triggerClassName, '"',
                false));

        return sb.toString();
    }
