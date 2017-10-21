    public int getBytesLength() {

        try {
            if (object instanceof Serializable) {
                byte[] data = InOutUtil.serialize((Serializable) object);

                return data.length;
            }
        } catch (Exception e) {}

        return 0;
    }
