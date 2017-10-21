    public void generate() throws IOException {
        Attributes attrs = new Attributes();
        attrs.put("type", "text/javascript");

        String base = ServletActionContext.getRequest().getContextPath();
        attrs.put("base", base);
        
        StringBuilder sb = new StringBuilder();
        if (base != null) {
            sb.append(base);
        }
        
        sb.append("/struts/utils.js");
        attrs.put("src", sb.toString());

        super.start("script", attrs);
        super.end("script");
    }
