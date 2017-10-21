    protected Map<String,String> replaceParameters(Map<String, String> orig, Map<String,String> vars) {
        Map<String, String> map = new LinkedHashMap<>();
        
        //this will set the group index references, like {1}
        for (String key : orig.keySet()) {
            map.put(key, convertParam(orig.get(key), vars));
        }
        
        //the values map will contain entries like name->"Lex Luthor" and 1->"Lex Luthor"
        //now add the non-numeric values
        for (String key: vars.keySet()) {
            if (!NumberUtils.isNumber(key)) {
                map.put(key, vars.get(key));
            }
        }
        
        return map;
    }
