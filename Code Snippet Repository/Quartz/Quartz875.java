    public String getProperties() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            properties.store(baos, "");

            return new String(baos.toByteArray());
        } catch (IOException ioe) {
            // should not happen
            return "";
        }
    }
