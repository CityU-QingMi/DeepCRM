    public void writeString(String s) {

        int temp = count;

        writeInt(0);

        if (s != null && s.length() != 0) {
            StringConverter.stringToUTFBytes(s, this);
            writeIntData(count - temp - INT_STORE_SIZE, temp);
        }
    }
