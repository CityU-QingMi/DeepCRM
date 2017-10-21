    public void setExtends(String value, Node.PageDirective n) {

        xtends = value;

/**/
/**/
/**/
/**/
/**/
        if (value.indexOf('.') < 0)
            n.addImport(value);
    }
