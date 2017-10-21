    synchronized MessageDigest getDigester() {

        if (digester == null) {
            try {
                digester = MessageDigest.getInstance(digestAlgo);
            } catch (NoSuchAlgorithmException e) {
                throw Error.error(ErrorCode.GENERAL_ERROR, e);
            }
        }

        return digester;
    }
