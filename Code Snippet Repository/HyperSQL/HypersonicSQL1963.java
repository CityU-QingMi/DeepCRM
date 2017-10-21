    public boolean setValue(int index,
                            Object value) throws IndexOutOfBoundsException {

        boolean result;
        Object  existing = objectValueTable[index];

        if (value == null) {
            result = existing != null;
        } else {
            result = !value.equals(existing);
        }

        objectValueTable[index] = value;

        return result;
    }
