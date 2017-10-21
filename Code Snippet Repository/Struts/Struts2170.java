    public void testIterableParameters() throws Exception {
        tag.setValue("/TestAction.action?p0=z");
        
        tag.doStartTag();
        //Iterable
        List<ValueHolder> list = new ArrayList<ValueHolder>();
        list.add(new ValueHolder("a"));
        list.add(new ValueHolder("b"));
        tag.component.addParameter("p1", list);
        
        //String[]
        tag.component.addParameter("p2", new String[] { "d", "e" });
        //ValueHolder[]
        tag.component.addParameter("p3", new ValueHolder[] {
                new ValueHolder("f"), new ValueHolder("g") });
        
        tag.doEndTag();
        
        assertEquals("/TestAction.action?p0=z&amp;p1=a&amp;p1=b&amp;p2=d&amp;p2=e&amp;p3=f&amp;p3=g", writer.toString());
    }
