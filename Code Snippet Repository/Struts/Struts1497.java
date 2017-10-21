    public void testSerializeUnknown() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        
        oos.writeObject(Location.UNKNOWN);
        oos.close();
        bos.close();
        
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        
        Object obj = ois.readObject();
        
        assertSame("unknown location", Location.UNKNOWN, obj);
    }
