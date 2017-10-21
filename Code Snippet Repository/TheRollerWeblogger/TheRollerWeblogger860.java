    public static String generateWSSEHeader(String userName, String password) 
    throws UnsupportedEncodingException {  
       
        byte[] nonceBytes = Long.toString(new Date().getTime()).getBytes();
        String nonce = WSSEUtilities.base64Encode(nonceBytes);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String created = sdf.format(new Date());
        
        String digest = WSSEUtilities.generateDigest(
                nonceBytes, created.getBytes("UTF-8"), password.getBytes("UTF-8"));
        
        StringBuilder header = new StringBuilder("UsernameToken Username=\"");
        header.append(userName);
        header.append("\", ");
        header.append("PasswordDigest=\"");
        header.append(digest);
        header.append("\", ");
        header.append("Nonce=\"");
        header.append(nonce);
        header.append("\", ");
        header.append("Created=\"");
        header.append(created);
        header.append("\"");
        return header.toString();
    }
