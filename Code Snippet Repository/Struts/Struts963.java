    public int doStartTag() throws JspException {
        // Source
        Object srcToSort;
        if (sourceAttr == null) {
            srcToSort = findValue("top");
        } else {
            srcToSort = findValue(sourceAttr);
        }
        if (! MakeIterator.isIterable(srcToSort)) { // see if source is Iteratable
            throw new JspException("source ["+srcToSort+"] is not iteratable");
        }

        // Comparator
        Object comparatorObj = findValue(comparatorAttr);
        if (! (comparatorObj instanceof Comparator)) {
            throw new JspException("comparator ["+comparatorObj+"] does not implements Comparator interface");
        }
        Comparator c = (Comparator) findValue(comparatorAttr);

        // SortIteratorFilter
        sortIteratorFilter = new SortIteratorFilter();
        sortIteratorFilter.setComparator(c);
        sortIteratorFilter.setSource(srcToSort);
        sortIteratorFilter.execute();

        // push sorted iterator into stack, so nexted tag have access to it
        getStack().push(sortIteratorFilter);
        if (var != null && var.length() > 0) {
            pageContext.setAttribute(var, sortIteratorFilter);
        }

        return EVAL_BODY_INCLUDE;
    }
