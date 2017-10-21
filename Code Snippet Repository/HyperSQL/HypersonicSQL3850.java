    public boolean equals(Object obj) {

        if (!(obj instanceof ConnectionSetting)) {
            return false;
        }

        ConnectionSetting other = (ConnectionSetting) obj;

        if (getName() == other.getName()) {
            return true;
        }

        if (getName() == null) {
            return false;
        }

        return getName().trim().equals(other.getName().trim());
    }
