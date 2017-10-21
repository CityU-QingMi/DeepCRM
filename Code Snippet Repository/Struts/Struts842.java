    public boolean match(Map<String, String> map, String data, RegexPatternMatcherExpression expr) {
        Matcher matcher = expr.getPattern().matcher(data);
        Map<Integer, String> params = expr.getParams();

        if (matcher.matches()) {
            map.put("0", data);
            
            //extract values and get param names from the expression
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String paramName = params.get(i);
                String value = matcher.group(i);
                
                //by name
                map.put(paramName, value);
                //by index so the old {1} still works
                map.put(String.valueOf(i), value);
            }

            return true;
        } else {
            return false;
        }
    }
