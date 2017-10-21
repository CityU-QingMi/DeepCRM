    public String toString()
    {
        StringBuffer strbuff = new StringBuffer();
        strbuff.append((groupId != null ? groupId : "")+",");
        strbuff.append((artifactId != null ? artifactId : "")+",");
        strbuff.append((classifier != null ? classifier : "")+",");
        strbuff.append((targetPath != null ? targetPath : "")+",");
        strbuff.append(""+skip+",");
        strbuff.append(""+filtered+",");
     
        if (includes != null)
        {
            Iterator<String> itor = includes.iterator();
            while (itor.hasNext())
            {
                strbuff.append(itor.next());
                if (itor.hasNext())
                    strbuff.append(";");
            }
        }
        
        strbuff.append(", ");

        if (excludes != null)
        {
            Iterator<String> itor = excludes.iterator();
            while (itor.hasNext())
            {
                strbuff.append(itor.next());
                if (itor.hasNext())
                    strbuff.append(";");
            }
        }
  
        return strbuff.toString();
    }
