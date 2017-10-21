    public int compareTo(Version other)
    {
        if (other == null)
        {
            throw new NullPointerException("other version is null");
        }
        if (this.legacyMajor < other.legacyMajor)
        {
            return -1;
        }
        if (this.legacyMajor > other.legacyMajor)
        {
            return 1;
        }
        if (this.major < other.major)
        {
            return -1;
        }
        if (this.major > other.major)
        {
            return 1;
        }
        if (this.revision < other.revision)
        {
            return -1;
        }
        if (this.revision > other.revision)
        {
            return 1;
        }
        if (this.update < other.update)
        {
            return -1;
        }
        if (this.update > other.update)
        {
            return 1;
        }
        return 0;
    }
