    public void writeJobDataFile(String version) throws Exception {
        Object obj = getTargetObject();
        
        FileOutputStream fos = new FileOutputStream(getSerializedFileName(version, obj.getClass()));
        
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        
        oos.writeObject(obj);

        oos.flush();
        fos.close();
        oos.close();
    }
