    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Location) {
            Location other = (Location)obj;
            return this.line == other.getLineNumber() && this.column == other.getColumnNumber()
                   && testEquals(this.uri, other.getURI())
                   && testEquals(this.description, other.getDescription());
        }
        
        return false;
    }
