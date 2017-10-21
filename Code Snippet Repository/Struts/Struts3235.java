    @Override
    protected void setUp() throws Exception {
        this.errors = new LinkedHashMap<String, List<String>>() {
            {
                put("field1", Arrays.asList("not good", "bad"));
                put("field2", Arrays.asList("bad to the bone"));
            }
        };
        this.fieldNames = new ArrayList<String>();
        this.fieldNames.add("field1");
        this.fieldNames.add("field2");

        //errors are needed to setup stack
        super.setUp();
        this.tag = new FieldError(stack, request, response);
    }
