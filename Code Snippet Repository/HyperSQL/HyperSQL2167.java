    public static String getSetCurrentPasswordDigestSQL(GranteeManager manager,
            String password, boolean isDigest) {

        if (!isDigest) {
            password = manager.digest(password);
        }

        StringBuffer sb = new StringBuffer(64);

        sb.append(Tokens.T_SET).append(' ');
        sb.append(Tokens.T_PASSWORD).append(' ').append(Tokens.T_DIGEST);
        sb.append(' ').append('\'').append(password).append('\'');

        return sb.toString();
    }
