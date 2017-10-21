    protected String newResponse(String method, String uri, String cnonce, String principal, String realm, String credentials, String nonce, String qop)
        throws Exception
    {       
        MessageDigest md = MessageDigest.getInstance("MD5");

        // calc A1 digest
        md.update(principal.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(realm.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(credentials.getBytes(StringUtil.__ISO_8859_1));
        byte[] ha1 = md.digest();
        // calc A2 digest
        md.reset();
        md.update(method.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(uri.getBytes(StringUtil.__ISO_8859_1));
        byte[] ha2=md.digest();

        md.update(TypeUtil.toString(ha1,16).getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(nonce.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(NC.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(cnonce.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(qop.getBytes(StringUtil.__ISO_8859_1));
        md.update((byte)':');
        md.update(TypeUtil.toString(ha2,16).getBytes(StringUtil.__ISO_8859_1));
        byte[] digest=md.digest();

        // check digest
        return encode(digest);
    }
