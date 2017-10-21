    public Object set(int index,
                      Object value) throws IndexOutOfBoundsException {

        checkRange(index);

        Object returnValue = objectKeyTable[index];

        objectKeyTable[index] = value;

        return returnValue;
    }
