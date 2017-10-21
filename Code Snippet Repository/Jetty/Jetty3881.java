        public String toString()
        {
            StringBuffer sb = new StringBuffer();
            sb.append("JspConfigDescriptor: \n");
            for (TaglibDescriptor taglib:_taglibs)
                sb.append(taglib+"\n");
            for (JspPropertyGroupDescriptor jpg:_jspPropertyGroups)
                sb.append(jpg+"\n");
            return sb.toString();
        }
