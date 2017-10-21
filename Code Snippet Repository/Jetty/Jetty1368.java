    public List<MappedResource<E>> getMatches(String path)
    {
        boolean isRootPath = "/".equals(path);
        
        List<MappedResource<E>> ret = new ArrayList<>();
        for (MappedResource<E> mr :_mappings)
        {
            switch (mr.getPathSpec().group)
            {
                case ROOT:
                    if (isRootPath)
                        ret.add(mr);
                    break;
                case DEFAULT:
                    if (isRootPath || mr.getPathSpec().matches(path))
                        ret.add(mr);
                    break;
                default:
                    if (mr.getPathSpec().matches(path))
                        ret.add(mr);
                    break;
            }
        }
        return ret;
    }
