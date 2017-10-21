    public static boolean validateMethod (String method)
    {
        if (method == null)
            return false;
        method = method.trim();
        return (method.equals(__FORM_AUTH) 
                || method.equals(__BASIC_AUTH) 
                || method.equals (__DIGEST_AUTH) 
                || method.equals (__CERT_AUTH) 
                || method.equals(__CERT_AUTH2)
                || method.equals(__SPNEGO_AUTH)
                || method.equals(__NEGOTIATE_AUTH));
    }
