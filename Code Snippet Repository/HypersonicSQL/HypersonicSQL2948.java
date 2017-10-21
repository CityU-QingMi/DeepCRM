    public String getSetUserPasswordDigestSQL(String password,
            boolean isDigest) {

        if (!isDigest) {
            password = granteeManager.digest(password);
        }

        StringBuffer sb = new StringBuffer(64);

        sb.append(Tokens.T_ALTER).append(' ');
        sb.append(Tokens.T_USER).append(' ');
        sb.append(getName().getStatementName()).append(' ');
        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_PASSWORD).append(' ').append(Tokens.T_DIGEST);
        sb.append(' ').append('\'').append(password).append('\'');

        return sb.toString();
    }
