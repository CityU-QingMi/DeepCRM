    public int getPort() {
        if (bind == null) {
            return -1;
        }
        String[] split = bind.split("\\:");
        if (split.length != 2) {
            throw new IllegalArgumentException("invalid bind format (should be IP:port)");
        }
        return Integer.parseInt(split[1]);
    }
