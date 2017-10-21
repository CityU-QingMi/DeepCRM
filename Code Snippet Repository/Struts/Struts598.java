    public OptGroup(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        super(stack);
        this.req = req;
        this.res = res;
        internalUiBean = new ListUIBean(stack, req, res) {
            protected String getDefaultTemplate() {
                return "empty";
            }
        };
    }
