    public final boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof LockFile) {
            LockFile other = (LockFile) obj;

            return (this.file == null) ? other.file == null
                                       : this.file.equals(other.file);
        }

        return false;
    }
