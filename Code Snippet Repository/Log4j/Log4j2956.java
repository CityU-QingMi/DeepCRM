    @Override
    protected void init() {
        super.init();
        this.message = null;
        this.marker = null;
        if (this.attributes == null) {
            this.attributes = new ArrayList<>();
        } else {
            this.attributes.clear();
        }
    }
