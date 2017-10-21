    public String render(final WeblogEntryComment comment, String text) {
        
        LOG.debug("starting value:\n" + text);
        
/**/
/**/
/**/
/**/
/**/
/**/
        StringBuilder buf = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new StringReader(text));
            
            String line = null;
            boolean insidePara = false;
            while((line = br.readLine()) != null) {
                
                if(!insidePara && line.trim().length() > 0) {
                    // start of a new paragraph
                    buf.append("\n<p>");
                    buf.append(line);
                    insidePara = true;
                } else if(insidePara && line.trim().length() > 0) {
                    // another line in an existing paragraph
                    buf.append("<br/>\n");
                    buf.append(line);
                } else if(insidePara && line.trim().length() < 1) {
                    // end of a paragraph
                    buf.append("</p>\n\n");
                    insidePara = false;
                }
            }
            
            // if the text ends without an empty line then we need to
            // terminate the last paragraph now
            if (insidePara) {
                buf.append("</p>\n\n");
            }
            
        } catch(Exception e) {
            LOG.warn("trouble rendering text.", e);
        }
        
        LOG.debug("ending value:\n" + buf.toString());
        
        return buf.toString();
    }
