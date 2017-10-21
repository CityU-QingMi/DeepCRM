    public boolean match(Map<String, String> map, String data, CompiledPattern expr) {

        if (data != null && data.length() > 0) {
            Matcher matcher = expr.getPattern().matcher(data);
            if (matcher.matches()) {
                for (int x=0; x<expr.getVariableNames().size(); x++)  {
                    map.put(expr.getVariableNames().get(x), matcher.group(x+1));
                }
                return true;
            }
        }
        return false;
    }
