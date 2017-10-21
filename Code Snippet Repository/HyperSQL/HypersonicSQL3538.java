    public int hashCode() {

        if (hashCode == 0) {
            int code = 0;

            for (int i = 0; i < data.length && i < 32; i++) {
                code = code * 31 + (0xff & data[i]);
            }

            hashCode = code;
        }

        return hashCode;
    }
