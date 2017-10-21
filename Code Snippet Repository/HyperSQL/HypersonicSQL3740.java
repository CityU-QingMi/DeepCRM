    public byte[] getBytes() {

        try {
            if (object instanceof Serializable) {
                return InOutUtil.serialize((Serializable) object);
            }
        } catch (Exception e) {}

        return new byte[]{};
    }
