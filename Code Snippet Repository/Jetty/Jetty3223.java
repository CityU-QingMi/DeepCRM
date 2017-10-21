    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
    {
        _id = in.readUTF();
        _contextPath = in.readUTF();
        _vhost = in.readUTF();
        
        _accessed = in.readLong();//accessTime
        _lastAccessed = in.readLong(); //lastAccessTime
        _created = in.readLong(); //time created
        _cookieSet = in.readLong();//time cookie was set
        _lastNode = in.readUTF(); //last managing node
        _expiry = in.readLong(); 
        _maxInactiveMs = in.readLong();
        _attributes = (Map<String,Object>)in.readObject();
    }
