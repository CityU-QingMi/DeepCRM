    public boolean contains(char[] array, char objectToFind) {
        if (array == null) {
            return false;
        }
        for (char anArray : array) {
            if (objectToFind == anArray) {
                return true;
            }
        }
        return false;
    }
