    @Setup
    public void setup() {
        for (int i = 0; i < 3; i++) {
            map3.put(String.valueOf(i), Long.valueOf(i));
            arraySet3.add(Long.valueOf(i));
            arrayList3.add(Long.valueOf(i));
        }
        map1.put(String.valueOf(1), Long.valueOf(1));
        arraySet1.add(Long.valueOf(1));
        arrayList1.add(Long.valueOf(1));
    }
