    @Override
    public Object[] getParameters() {
        List<Object[]> objectList = new ArrayList<>();
        int count = 0;
        for (StructuredDataMessage msg : structuredDataMessageList) {
            Object[] objects = msg.getParameters();
            if (objects != null) {
                objectList.add(objects);
                count += objects.length;
            }
        }
        Object[] objects = new Object[count];
        int index = 0;
        for (Object[] objs : objectList) {
           for (Object obj : objs) {
               objects[index++] = obj;
           }
        }
        return objects;
    }
