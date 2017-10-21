    public void setLocaleStorage(String storageName) {
        if (storageName == null || "".equals(storageName)) {
            this.storage = Storage.NONE;
        } else {
            try {
                this.storage = Storage.valueOf(storageName.toUpperCase());
            } catch (IllegalArgumentException e) {
                LOG.warn(new ParameterizedMessage("Wrong storage name [{}] was defined, falling back to {}", storageName, Storage.SESSION), e);
                this.storage = Storage.SESSION;
            }
        }
    }
