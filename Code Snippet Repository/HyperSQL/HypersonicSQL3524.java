    public String getNameString() {

        StringBuffer sb = new StringBuffer();

        sb.append(dataType.getNameString()).append(' ');
        sb.append(Tokens.T_ARRAY);

        if (maxCardinality != defaultArrayCardinality) {
            sb.append('[').append(maxCardinality).append(']');
        }

        return sb.toString();
    }
