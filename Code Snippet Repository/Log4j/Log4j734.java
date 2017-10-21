    static StackTraceElement convertString(final String s) {
        final int open = s.indexOf("(");

        final String classMethod = s.substring(0, open);
        final String className = classMethod.substring(0, classMethod.lastIndexOf("."));
        final String methodName = classMethod.substring(classMethod.lastIndexOf(".") + 1);

        final String parenthesisContents = s.substring(open + 1, s.indexOf(")"));

        String fileName = null;
        int lineNumber = UNKNOWN_SOURCE;
        if ("Native Method".equals(parenthesisContents)) {
            lineNumber = NATIVE_METHOD;
        } else if (!"Unknown Source".equals(parenthesisContents)) {
            final int colon = parenthesisContents.indexOf(":");
            if (colon > UNKNOWN_SOURCE) {
                fileName = parenthesisContents.substring(0, colon);
                try {
                    lineNumber = Integer.parseInt(parenthesisContents.substring(colon + 1));
                } catch (final NumberFormatException ignore) {
                    // we don't care
                }
            } else {
                fileName = parenthesisContents.substring(0);
            }
        }

        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }
