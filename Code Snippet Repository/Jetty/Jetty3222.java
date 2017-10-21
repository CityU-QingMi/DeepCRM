    private void writeObject(java.io.ObjectOutputStream out) throws IOException
    {  
        out.writeUTF(_id); //session id
        out.writeUTF(_contextPath); //context path
        out.writeUTF(_vhost); //first vhost

        out.writeLong(_accessed);//accessTime
        out.writeLong(_lastAccessed); //lastAccessTime
        out.writeLong(_created); //time created
        out.writeLong(_cookieSet);//time cookie was set
        out.writeUTF(_lastNode); //name of last node managing
  
        out.writeLong(_expiry); 
        out.writeLong(_maxInactiveMs);
        out.writeObject(_attributes);
    }
