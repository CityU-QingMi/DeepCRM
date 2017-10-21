    public static String encode(String email) {
        StringBuilder result = new StringBuilder();
        try {
            char[] hexString = Hex.encodeHex(email.getBytes("UTF-8"));
            for (int i = 0; i < hexString.length; i++) {
                if (i % 2 == 0) {
                    result.append("%");
                }
                result.append(hexString[i]);
            }
        } catch (UnsupportedEncodingException e) {
            return email;
        }
        
        return result.toString();
    }
