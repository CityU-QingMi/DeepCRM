    public Action getAction() {
        return new ActionSupport() {
            public Converter getMyConverter() {
                return new Converter() {
                    public Object convert(String value) throws Exception {
                        return "myConverter-"+value;
                    }
                };
            }

            public int getMyCount() {
                return 3;
            }
        };
    }
