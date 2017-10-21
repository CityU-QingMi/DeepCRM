    protected void sort()
    {
        _sorted=true;

        Double last = ZERO;
        int lastSecondaryOrder = Integer.MIN_VALUE;

        for (int i = _values.size(); i-- > 0;)
        {
            String v = _values.get(i);
            Double q = _quality.get(i);

            int compare=last.compareTo(q);
            if (compare>0 || (compare==0 && _secondaryOrdering.apply(v)<lastSecondaryOrder))
            {
                _values.set(i, _values.get(i + 1));
                _values.set(i + 1, v);
                _quality.set(i, _quality.get(i + 1));
                _quality.set(i + 1, q);
                last = ZERO;
                lastSecondaryOrder=0;
                i = _values.size();
                continue;
            }

            last=q;
            lastSecondaryOrder=_secondaryOrdering.apply(v);
        }
        
        int last_element=_quality.size();
        while(last_element>0 && _quality.get(--last_element).equals(ZERO))
        {
            _quality.remove(last_element);
            _values.remove(last_element);
        }
    }
