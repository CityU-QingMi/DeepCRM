    protected String generateThumbs(Queue<Map<String,String>> results)
    {
        StringBuilder thumbs = new StringBuilder();
        for (Map<String, String> m : results)
        {
            if (!m.containsKey("GalleryURL"))
                continue;
                
            thumbs.append("<a href=\""+m.get("ViewItemURLForNaturalSearch")+"\">");
            thumbs.append("<img class='thumb' border='1px' height='25px'"+
                        " src='"+m.get("GalleryURL")+"'"+
                        " title='"+m.get("Title")+"'"+
                        "/>");
            thumbs.append("</a>&nbsp;");
        }
        return thumbs.toString();
    }
