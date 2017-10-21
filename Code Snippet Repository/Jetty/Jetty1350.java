    @Override
    public void clear()
    {
        _exactMap.clear();
        _prefixMap=new ArrayTernaryTrie<>(false);
        _suffixMap=new ArrayTernaryTrie<>(false);
        _default=null;
        _defaultSingletonList=null;
        _prefixDefault=null;
        super.clear();
    }
