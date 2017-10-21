    char getChar(Session session, Object o, int i) {

        char c;

        if (isBinary) {
            c = (char) ((BlobData) o).getBytes()[i];
        } else {
            if (o instanceof char[]) {
                c = ((char[]) o)[i];
            } else if (o instanceof ClobData) {
                c = ((ClobData)o).getChars(session,i,1)[0];
            } else {
                c = ((String) o).charAt(i);
            }
        }

        return c;
    }
