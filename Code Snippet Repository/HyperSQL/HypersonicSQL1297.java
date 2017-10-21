    private static void varParser(String inVarString,
                                  Map<String, String> varMap,
                                  boolean lowerCaseKeys)
                                  throws PrivateException {

        int       equals;
        String    var;
        String    val;

        if (varMap == null) {
            throw new IllegalArgumentException(
                    "varMap is null in SqlTool.varParser call");
        }
        if (inVarString == null) {
            throw new IllegalArgumentException(
                    "inVarString is null in SqlTool.varParser call");
        }
        boolean escapesPresent = inVarString.indexOf("\\,") > -1;
        String  varString = escapesPresent ?
                inVarString.replace("\\,", "\u0002")
                : inVarString;

        for (String token : varString.split("\\s*,\\s*")) {
            equals = token.indexOf('=');

            if (equals < 1) {
                throw new PrivateException(
                    SqltoolRB.SqlTool_varset_badformat.getString());
            }

            var = token.substring(0, equals).trim();
            val = token.substring(equals + 1).trim();
            if (escapesPresent) {
                val = val.replace("\u0002", ",");
            }

            if (var.length() < 1) {
                throw new PrivateException(
                    SqltoolRB.SqlTool_varset_badformat.getString());
            }

            if (lowerCaseKeys) {
                var = var.toLowerCase();
            }

            varMap.put(var, val);
        }
    }
