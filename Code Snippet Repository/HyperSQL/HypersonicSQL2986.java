    protected BlobData readBlob() {

        String s = readString();

        if (s == null) {
            return null;
        }

        s = s.trim();

        if (s.length() == 0) {
            return null;
        }

        long id = Long.parseLong(s);

        return new BlobDataID(id);
    }
