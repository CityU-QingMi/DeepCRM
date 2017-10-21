    public String getDataImpactString() {

        StringBuffer sb = new StringBuffer();

        switch (dataImpact) {

            case NO_SQL :
                sb.append(Tokens.T_NO).append(' ').append(Tokens.T_SQL);
                break;

            case CONTAINS_SQL :
                sb.append(Tokens.T_CONTAINS).append(' ').append(Tokens.T_SQL);
                break;

            case READS_SQL :
                sb.append(Tokens.T_READS).append(' ').append(
                    Tokens.T_SQL).append(' ').append(Tokens.T_DATA);
                break;

            case MODIFIES_SQL :
                sb.append(Tokens.T_MODIFIES).append(' ').append(
                    Tokens.T_SQL).append(' ').append(Tokens.T_DATA);
                break;
        }

        return sb.toString();
    }
