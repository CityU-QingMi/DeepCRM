    @Override
    public Result authenticate(Request request, ContentResponse response, HeaderInfo headerInfo, Attributes context)
    {
        Map<String, String> params = parseParameters(headerInfo.getParameters());
        String nonce = params.get("nonce");
        if (nonce == null || nonce.length() == 0)
            return null;
        String opaque = params.get("opaque");
        String algorithm = params.get("algorithm");
        if (algorithm == null)
            algorithm = "MD5";
        MessageDigest digester = getMessageDigest(algorithm);
        if (digester == null)
            return null;
        String serverQOP = params.get("qop");
        String clientQOP = null;
        if (serverQOP != null)
        {
            List<String> serverQOPValues = StringUtil.csvSplit(null,serverQOP,0,serverQOP.length());
            if (serverQOPValues.contains("auth"))
                clientQOP = "auth";
            else if (serverQOPValues.contains("auth-int"))
                clientQOP = "auth-int";
        }

        String realm = getRealm();
        if (ANY_REALM.equals(realm))
            realm = headerInfo.getRealm();
        return new DigestResult(headerInfo.getHeader(), response.getContent(), realm, user, password, algorithm, nonce, clientQOP, opaque);
    }
