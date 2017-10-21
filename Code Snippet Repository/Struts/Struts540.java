    private void validateArrayElements(Object[] array, String fieldName, String visitorContext) throws ValidationException {
        if (array == null) {
            return;
        }

        for (int i = 0; i < array.length; i++) {
            Object o = array[i];
            if (o != null) {
                validateObject(fieldName + "[" + i + "]", o, visitorContext);
            }
        }
    }
