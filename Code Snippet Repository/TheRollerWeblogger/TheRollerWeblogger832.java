    public static String getEncodedTagsString(List tags) {
        StringBuilder tagsString = new StringBuilder();
        if(tags != null && tags.size() > 0) {
            String tag;
            Iterator tagsIT = tags.iterator();
            
            // do first tag
            tag = (String) tagsIT.next();
            tagsString.append(encode(tag));
            
            // do rest of tags, joining them with a '+'
            while(tagsIT.hasNext()) {
                tag = (String) tagsIT.next();
                tagsString.append("+");
                tagsString.append(encode(tag));
            }
        }
        return tagsString.toString();
    }
