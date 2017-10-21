    protected String convertParam(String val, Map<String, String> vars) {
        if (val == null) {
            return null;
        } 
        
        int len = val.length();
        StringBuilder ret = new StringBuilder();
        char c;
        String varVal;
        for (int x=0; x<len; x++) {
            c = val.charAt(x);
            if (x < len - 2 && 
                    c == '{' && '}' == val.charAt(x+2)) {
                varVal = (String)vars.get(String.valueOf(val.charAt(x + 1)));
                if (varVal != null) {
                    ret.append(varVal);
                } 
                x += 2;
            } else {
                ret.append(c);
            }
        }
        
        return ret.toString();
    }
