    String createDebugString(Object[] args, Exception exception) {
        StringBuffer buffer = new StringBuffer();
        printExceptionTraceToBuffer(exception, buffer);
        buffer.append("\nException in GXP: ").append(gxpClass.getName()).append(". Params:");
        int index = 2;
        for (Param param : getParams()) {
            try {
                Object arg = args[index++];
                String typesMatch = "n/a (null)";
                if (arg != null) {
                    if (doesArgumentTypeMatchParamType(param, arg)) {
                        typesMatch = "YES";
                    } else {
                        typesMatch = "NO";
                    }
                }
                buffer.append("\n  ")
                        .append(param.toString())
                        .append(" = ")
                        .append(arg)
                        .append("; ")
                        .append("[types match? ")
                        .append(typesMatch)
                        .append("]");
            } catch (Exception e) {
                buffer.append(" >Error getting information for param # ").append(index).append("< ");
            }
        }
        buffer.append("\nStack trace: ");
        return buffer.toString();
    }
