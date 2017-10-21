    public StructuredDataId makeId(final String defaultId, final int anEnterpriseNumber) {
        String id;
        String[] req;
        String[] opt;
        if (anEnterpriseNumber <= 0) {
            return this;
        }
        if (this.name != null) {
            id = this.name;
            req = this.required;
            opt = this.optional;
        } else {
            id = defaultId;
            req = null;
            opt = null;
        }

        return new StructuredDataId(id, anEnterpriseNumber, req, opt);
    }
