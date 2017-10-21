    public void add(Object[] data) {

        ensureCapacity();

        dataTable[size] = data;

        size++;

        if (rowMap != null) {
            rowMap.put(data, data);
        }

        if (idMap != null) {
            Long id = (Long) data[visibleColumnCount];

            idMap.put(id.longValue(), data);
        }
    }
