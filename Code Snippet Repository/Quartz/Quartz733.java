    protected Object deserialize(String version, Class<?> clazz) throws Exception {
        InputStream is = getClass().getResourceAsStream(getSerializedFileName(version, clazz));
        
        ObjectInputStream ois = new ObjectInputStream(is);
        
        Object obj = (Object)ois.readObject();

        ois.close();
        is.close();

        return obj;
    }
