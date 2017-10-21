    public String render(WeblogEntry entry, String str) {
        
        if(str == null || str.trim().equals("")) {
            return "";
        }
        
        mLogger.debug("Rendering string of length "+str.length());
        
/**/
/**/
/**/
/**/
/**/
        StringBuilder buf = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new StringReader(str));
            
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
            if(insidePara) {
                buf.append("</p>\n\n");
            }
            
        } catch(Exception e) {
            mLogger.warn("trouble rendering text.", e);
            return str;
        }
        
        return buf.toString();
    }
