    private Exception newPlainException(Exception e) {
        String type = e.getClass().getName();
        if(type.startsWith("java.") || type.startsWith("javax.")) {
            return e;
        } else {
            Exception result = new Exception(e.getMessage());
            result.setStackTrace(e.getStackTrace());
            return result;
        }
    }
