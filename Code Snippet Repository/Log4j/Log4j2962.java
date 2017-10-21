    @Before
    public void setUp() {
        this.output = new ByteArrayOutputStream();
        this.writer = new OutputStreamWriter(this.output, UTF8);

        this.context = new MockPageContext() {
            private final MockJspWriter jspWriter = new MockJspWriter(writer);
            @Override
            public JspWriter getOut() {
                return this.jspWriter;
            }
        };

        this.tag = new DumpTag();
        this.tag.setPageContext(this.context);
    }
