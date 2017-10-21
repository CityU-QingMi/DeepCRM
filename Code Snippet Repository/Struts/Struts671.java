    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(args);
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        result = prime * result + ((defaultMessage == null) ? 0 : defaultMessage.hashCode());
        result = prime * result + ((textKey == null) ? 0 : textKey.hashCode());
        return result;
    }
