    public int bytesLeftInBlock() {

        int modulus = (int) (bytesWritten % 512L);

        if (modulus == 0) {
            return 0;
        }

        return 512 - modulus;
    }
