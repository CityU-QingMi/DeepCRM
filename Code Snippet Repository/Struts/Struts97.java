    public Object handleUnknownMethod(Object action, String methodName) throws NoSuchMethodException {
        for (UnknownHandler unknownHandler : unknownHandlers) {
            Object result = unknownHandler.handleUnknownActionMethod(action, methodName);
            if (result != null) {
                return result;
            }
        }

        if (unknownHandlers.isEmpty()) {
            throw new NoSuchMethodException(String.format("No UnknownHandlers defined to handle method [%s]", methodName));
        } else {
            throw new NoSuchMethodException(String.format("None of defined UnknownHandlers can handle method [%s]", methodName));
        }
    }
