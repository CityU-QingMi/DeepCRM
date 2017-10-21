    public String getDefinition() {

        StringBuffer sb = new StringBuffer();

        sb.append(dataType.getDefinition()).append(' ');
        sb.append(Tokens.T_ARRAY);

        if (maxCardinality != defaultArrayCardinality) {
            sb.append('[').append(maxCardinality).append(']');
        }

        return sb.toString();
    }
