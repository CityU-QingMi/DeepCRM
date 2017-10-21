    public int hashCode() {
        if (!hashComputed) {
            hash = 7;
            hash = 83 * hash + this.formatID;
            hash = 83 * hash + Arrays.hashCode(this.txID);
            hash = 83 * hash + Arrays.hashCode(this.txBranch);
            hashComputed = true;
        }
        return hash;
    }
