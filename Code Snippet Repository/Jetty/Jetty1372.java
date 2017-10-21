    @Override
    public int compareTo(PathSpec other)
    {
        // Grouping (increasing)
        int diff = this.group.ordinal() - other.group.ordinal();
        if (diff != 0)
        {
            return diff;
        }

        // Spec Length (decreasing)
        diff = other.specLength - this.specLength;
        if (diff != 0)
        {
            return diff;
        }

        // Path Spec Name (alphabetical)
        return this.pathSpec.compareTo(other.pathSpec);
    }
