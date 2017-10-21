    public String getDefinition() {

        long   factor     = precision;
        String multiplier = null;

        if (precision % (1024) == 0) {
            if (precision % (1024 * 1024 * 1024) == 0) {
                factor     = precision / (1024 * 1024 * 1024);
                multiplier = Tokens.T_G_FACTOR;
            } else if (precision % (1024 * 1024) == 0) {
                factor     = precision / (1024 * 1024);
                multiplier = Tokens.T_M_FACTOR;
            } else {
                factor     = precision / (1024);
                multiplier = Tokens.T_K_FACTOR;
            }
        }

        StringBuffer sb = new StringBuffer(16);

        sb.append(getNameString());
        sb.append('(');
        sb.append(factor);

        if (multiplier != null) {
            sb.append(multiplier);
        }

        sb.append(')');

        return sb.toString();
    }
