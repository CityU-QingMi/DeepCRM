    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LocalizedMessage other = (LocalizedMessage) obj;
        if (!Arrays.equals(args, other.args)) {
            return false;
        }
        if (clazz == null) {
            if (other.clazz != null) {
                return false;
            }
        } else if (!clazz.equals(other.clazz)) {
            return false;
        }
        if (defaultMessage == null) {
            if (other.defaultMessage != null) {
                return false;
            }
        } else if (!defaultMessage.equals(other.defaultMessage)) {
            return false;
        }
        if (textKey == null) {
            if (other.textKey != null) {
                return false;
            }
        } else if (!textKey.equals(other.textKey)) {
            return false;
        }
        return true;
    }
