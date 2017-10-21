    public String posSubst(String s, String[] subs, int behavior) {
        Matcher matcher = posPattern.matcher(s);
        int previousEnd = 0;
        StringBuffer sb = new StringBuffer();
        String varValue;
        int varIndex;
        String condlVal;  // Conditional : value
        while (matcher.find()) {
            varIndex = Integer.parseInt(matcher.group(1)) - 1;
            condlVal = ((matcher.groupCount() > 1) ? matcher.group(2) : null);
            varValue = ((varIndex < subs.length) ? subs[varIndex] : null);
            if (condlVal != null) {
                // Replace varValue (the value to be substituted), with
                // the post-:+ portion of the expression.
                varValue = ((varValue == null)
                        ? ""
                        : condlVal.replaceAll("\\Q%" + (varIndex+1) + "\\E\\b",
                                Matcher.quoteReplacement(varValue)));
            }
            // System.err.println("Behavior: " + behavior);
            if (varValue == null) switch (behavior) {
                case THROW_BEHAVIOR:
                    throw new RuntimeException(
                            Integer.toString(subs.length)
                            + " positional values given, but property string "
                            + "contains (" + matcher.group() + ").");
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
