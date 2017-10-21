    public Entry add(HttpField field)
    {
        Entry entry=new Entry(field);
        int size = entry.getSize();
        if (size>_maxDynamicTableSizeInBytes)
        {
            if (LOG.isDebugEnabled())
                LOG.debug(String.format("HdrTbl[%x] !added size %d>%d",hashCode(),size,_maxDynamicTableSizeInBytes));
            return null;
        }
        _dynamicTableSizeInBytes+=size;
        _dynamicTable.add(entry);
        _fieldMap.put(field,entry);
        _nameMap.put(StringUtil.asciiToLowerCase(field.getName()),entry);

        if (LOG.isDebugEnabled())
            LOG.debug(String.format("HdrTbl[%x] added %s",hashCode(),entry));
        _dynamicTable.evict();
        return entry;
    }
