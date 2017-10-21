    public CompiledPattern compilePattern(String data) {
        StringBuilder regex = new StringBuilder();
        if (data != null && data.length() > 0) {
            List<String> varNames = new ArrayList<>();
            StringBuilder varName = null;
            for (int x=0; x<data.length(); x++) {
                char c = data.charAt(x);
                switch (c) {
                    case '{' :  varName = new StringBuilder(); break;
                    case '}' :  if (varName == null) {
                                    throw new IllegalArgumentException("Mismatched braces in pattern");
                                }
                                varNames.add(varName.toString());
                                regex.append("([^/]+)");
                                varName = null;
                                break;
                    default  :  if (varName == null) {
                                    regex.append(c);
                                } else {
                                    varName.append(c);
                                }
                }
            }
            return new CompiledPattern(Pattern.compile(regex.toString()), varNames);
        }
        return null;
    }
