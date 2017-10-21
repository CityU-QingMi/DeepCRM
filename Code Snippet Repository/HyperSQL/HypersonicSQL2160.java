    public String getExpandedString(String key, int behavior) {
        String s = getString(key);
        Matcher matcher = sysPropVarPattern.matcher(s);
        int previousEnd = 0;
        StringBuffer sb = new StringBuffer();
        String varName, varValue;
        String condlVal;  // Conditional : value
        while (matcher.find()) {
            varName = matcher.group(1);
            condlVal = ((matcher.groupCount() > 1) ? matcher.group(2) : null);
            varValue = System.getProperty(varName);
            if (condlVal != null) {
                // Replace varValue (the value to be substituted), with
                // the post-:+ portion of the expression.
                varValue = ((varValue == null)
                        ? ""
                        : condlVal.replaceAll("\\Q$" + varName + "\\E\\b",
                                Matcher.quoteReplacement(varValue)));
            }
            if (varValue == null) switch (behavior) {
                case THROW_BEHAVIOR:
                    throw new RuntimeException(
                            "No Sys Property set for variable '"
                            + varName + "' in property value ("
                            + s + ").");
                case EMPTYSTRING_BEHAVIOR:
                    varValue = "";
                    break;
                case NOOP_BEHAVIOR:
                    break;
                default:
                    throw new RuntimeException(
                            "Undefined value for behavior: " + behavior);
            }
            sb.append(s.substring(previousEnd, matcher.start())
                        + ((varValue == null) ? matcher.group() : varValue));
            previousEnd = matcher.end();
        }
        return (previousEnd < 1) ? s
                                 : (sb.toString() + s.substring(previousEnd));
    }
