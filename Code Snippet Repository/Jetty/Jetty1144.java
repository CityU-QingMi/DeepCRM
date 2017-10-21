        private void evict()
        {
            while (_dynamicTableSizeInBytes>_maxDynamicTableSizeInBytes)
            {
                Entry entry = _entries[_offset];
                _entries[_offset]=null;
                _offset = (_offset+1)%_entries.length;
                _size--;
                if (LOG.isDebugEnabled())
                    LOG.debug(String.format("HdrTbl[%x] evict %s",HpackContext.this.hashCode(),entry));
                _dynamicTableSizeInBytes-=entry.getSize();
                entry._slot=-1;
                _fieldMap.remove(entry.getHttpField());
                String lc=StringUtil.asciiToLowerCase(entry.getHttpField().getName());
                if (entry==_nameMap.get(lc))
                    _nameMap.remove(lc);

            }
            if (LOG.isDebugEnabled())
                LOG.debug(String.format("HdrTbl[%x] entries=%d, size=%d, max=%d",HpackContext.this.hashCode(),_dynamicTable.size(),_dynamicTableSizeInBytes,_maxDynamicTableSizeInBytes));
        }
