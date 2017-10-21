    public static long reassignValue(String assignee,
            Map<String, String> valMap, String opStr, String expr) {
        long outVal = 0;
        try {
            outVal = Long.parseLong(valMap.get(assignee));
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException(
                    "Can not perform a self-operation on a non-integer: "
                    + assignee);
        }
        Long rhValObj = (expr == null || expr.trim().length() < 1) ? null
                : Long.valueOf(
                        new Calculator(expr, valMap).reduce(0, false));
        if (opStr.equals("++")) {
            if (rhValObj != null)
                throw new IllegalStateException(
                        "++ operator takes no right hand operand");
            return 1 + outVal;
        }
        if (opStr.equals("--")) {
            if (rhValObj != null)
                throw new IllegalStateException(
                        "++ operator takes no right hand operand");
            return outVal - 1;
        }
        if (rhValObj == null)
            throw new IllegalStateException(
                    "Operator requires a right hand operand: " + opStr);
        long rhVal = rhValObj.intValue();
        if (opStr.equals("+=")) {
            outVal += rhVal;
        } else if (opStr.equals("-=")) {
            outVal -= rhVal;
        } else if (opStr.equals("*=")) {
            outVal *= rhVal;
        } else if (opStr.equals("/=")) {
            outVal /= rhVal;
        } else if (opStr.equals("%=")) {
            outVal %= rhVal;
        } else {
            throw new IllegalStateException("Unsupported operator: " + opStr);
        }
        return outVal;
    }
