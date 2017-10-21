    public boolean next() throws JRException {
        if (firstTimeThrough) {
            firstTimeThrough = false;
        } else {
            valueStack.pop();
        }

        if ((iterator != null) && (iterator.hasNext())) {
            valueStack.push(iterator.next());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Pushed next value: {}", valueStack.findValue("."));
            }

            return true;
        } else {
            LOG.debug("No more values");

            return false;
        }
    }
