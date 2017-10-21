    protected String readResultCode(Object methodResult) {
        if (methodResult == null) {
            return null;
        }
        if (methodResult instanceof HttpHeaders) {
            return ((HttpHeaders) methodResult).getResultCode();
        } else {
            return methodResult.toString();
        }
    }
